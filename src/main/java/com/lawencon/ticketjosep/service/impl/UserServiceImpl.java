package com.lawencon.ticketjosep.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.constant.RoleCode;
import com.lawencon.ticketjosep.dao.CompanyDao;
import com.lawencon.ticketjosep.dao.FileDao;
import com.lawencon.ticketjosep.dao.ProfileDao;
import com.lawencon.ticketjosep.dao.RoleDao;
import com.lawencon.ticketjosep.dao.UserDao;
import com.lawencon.ticketjosep.dto.InsertResDto;
import com.lawencon.ticketjosep.dto.UpdatePhotoResDto;
import com.lawencon.ticketjosep.dto.UpdateResDto;
import com.lawencon.ticketjosep.dto.account.LoginReqDto;
import com.lawencon.ticketjosep.dto.account.LoginResDto;
import com.lawencon.ticketjosep.dto.file.FileDto;
import com.lawencon.ticketjosep.dto.profile.ProfilePhotoUpdateReqDto;
import com.lawencon.ticketjosep.dto.profile.ProfileResDto;
import com.lawencon.ticketjosep.dto.profile.ProfileUpdateReqDto;
import com.lawencon.ticketjosep.dto.user.ChangePasswordReqDto;
import com.lawencon.ticketjosep.dto.user.UserInsertReqDto;
import com.lawencon.ticketjosep.dto.user.UsersResDto;
import com.lawencon.ticketjosep.model.Company;
import com.lawencon.ticketjosep.model.File;
import com.lawencon.ticketjosep.model.Profile;
import com.lawencon.ticketjosep.model.Role;
import com.lawencon.ticketjosep.model.User;
import com.lawencon.ticketjosep.service.EmailService;
import com.lawencon.ticketjosep.service.PrincipalService;
import com.lawencon.ticketjosep.service.UserService;
import com.lawencon.ticketjosep.util.GeneratorUtil;
import com.lawencon.ticketjosep.validation.CustomException;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserDao userDao;
	private final ProfileDao profileDao;
	private final FileDao fileDao;
	private final RoleDao roleDao;
	private final CompanyDao companyDao;
	private final PasswordEncoder passwordEncoder;
	private final EmailService emailService;
	private final PrincipalService principalService;
	
	@PersistenceContext
	private EntityManager em;
	
	public UserServiceImpl(UserDao userDao, ProfileDao profileDao, 
			FileDao fileDao, RoleDao roleDao, 
			CompanyDao companyDao, PasswordEncoder passwordEncoder, 
			EmailService emailService, PrincipalService principalService) {
		this.userDao = userDao;
		this.profileDao = profileDao;
		this.fileDao = fileDao;
		this.roleDao = roleDao;
		this.companyDao = companyDao;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
		this.principalService = principalService;
	}

	@Override
	public List<UsersResDto> getUserByRoleCode(String roleCode) {
		final List<UsersResDto> usersResDtos = new ArrayList<>();
		List<User> users = null;
		if (roleCode == null) {
			users = userDao.getAll();
		} else {
			users = userDao.getUserByRoleCode(roleCode);
		}
		users.forEach(u ->{
			final UsersResDto usersResDto = new UsersResDto();
			usersResDto.setId(u.getId());
			usersResDto.setName(u.getProfile().getProfileName());
			usersResDto.setPhone(u.getProfile().getProfilePhone());
			usersResDto.setAddress(u.getProfile().getProfileAddress());
			usersResDto.setCompanyName(u.getCompany().getCompanyName());
			usersResDto.setRoleName(u.getRole().getRoleName());
			usersResDtos.add(usersResDto);
		});
		
		return usersResDtos;
	}

	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto data) {
		InsertResDto insertedUser = new InsertResDto();
		final Role role = roleDao.getById(data.getRoleId());	
		final Company company = companyDao.getById(data.getCompanyId());
		
		if(RoleCode.CUSTOMER.roleCode.equals(role.getRoleCode()) && data.getCompanyId() == 1) {
			throw new CustomException("Error! Company " + company.getCompanyName() + " is not for " + RoleCode.CUSTOMER.roleName);
		} else {

			final Profile profile = new Profile();
			if (data.getFile() != null && !data.getFile().isEmpty() ) {
					final File newFile = new File();
					newFile.setFiles(data.getFile());
					newFile.setFileFormat(data.getFileFormat());
					newFile.setCreatedBy(principalService.getId());
					fileDao.insertFile(newFile);
					
					profile.setFile(newFile);
			} 
				
			profile.setProfileName(data.getProfileName());
			profile.setProfilePhone(data.getProfilePhone());
			profile.setProfileAddress(data.getProfileAddress());
			profile.setCreatedBy(principalService.getId());
				
			profileDao.insertProfile(profile);
				
			final User user = new User();
			user.setEmail(data.getEmail());
			
			final String password = GeneratorUtil.generateAlphaNum(5);
			final String passwordEncoded = passwordEncoder.encode(password);
			
			user.setPassword(passwordEncoded);
			user.setRole(role);
			user.setCompany(company);
			user.setProfile(profile);
			user.setCreatedBy(principalService.getId());
				
			final User newUser = userDao.insert(user);	
				
			final String msg = "your password account : " + password;
			
			if(newUser != null) {
				insertedUser.setId(newUser.getId());
				insertedUser.setMsg("Account Created Successfully");
				emailService.sendEmail("Your account password for ticket management system", msg, newUser.getEmail());
			} 
		}

		return insertedUser;
	}

	@Transactional
	@Override
	public UpdateResDto update(ProfileUpdateReqDto data) {
		UpdateResDto resDto = new UpdateResDto();
		final Profile profile = profileDao.getById(data.getProfileId());
		profile.setProfileName(data.getProfileName());
		profile.setProfilePhone(data.getProfilePhone());
		profile.setProfileAddress(data.getProfileAddress());
		profile.setVer(data.getVersion());
		profile.setUpdatedBy(principalService.getId());
			
		final User user = userDao.getById(principalService.getId());
		user.setProfile(profile);
		user.setUpdatedBy(principalService.getId());

		profileDao.update(profile);
		
		resDto.setVer(profile.getVer());
		resDto.setMsg("Update Account Successfully");
		
		return resDto;
	}

	@Transactional
	@Override
	public UpdatePhotoResDto updatePhoto(ProfilePhotoUpdateReqDto data) {
		UpdatePhotoResDto updateResDto = new UpdatePhotoResDto();
		final Profile profile = profileDao.getById(data.getProfileId());
		
		Long fileId = 0L;
		if(profile.getFile() != null) {
			fileId = profile.getFile().getId();
		}
			
		final File newFile = new File();
		newFile.setFiles(data.getFile());
		newFile.setFileFormat(data.getFileFormat());
		newFile.setCreatedBy(principalService.getId());
		fileDao.insertFile(newFile);
			
		profile.setFile(newFile);
		profile.setUpdatedBy(principalService.getId());
			
		profileDao.update(profile);
		
		updateResDto.setVer(profile.getVer());
		updateResDto.setFileId(profile.getFile().getId());
			
		fileDao.deleteFileById(fileId);	
			
		updateResDto.setMsg("Photo updated successfully");
		
		return updateResDto;
	}

	@Override
	public LoginResDto login(LoginReqDto data) {
		final LoginResDto loginResDto = new LoginResDto();
		
		final User user = userDao.getByEmail(data.getEmail());
		
		if(user != null) {
			loginResDto.setUserId(user.getId());
			loginResDto.setProfileName(user.getProfile().getProfileName());
			loginResDto.setRoleCode(user.getRole().getRoleCode());
			if(user.getProfile().getFile()!= null) {
				loginResDto.setPictureId(user.getProfile().getFile().getId());	
			}
		}
		return loginResDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByEmail(username);
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), new ArrayList<>());
		} 
		throw new UsernameNotFoundException("Email not found!");
	}

	@Override
	public ProfileResDto getUserDetail() {
		final User user = userDao.getById(principalService.getId());
		
		final ProfileResDto response = new ProfileResDto();
		response.setEmail(user.getEmail());
		response.setName(user.getProfile().getProfileName());
		response.setPhone(user.getProfile().getProfilePhone());
		response.setAddress(user.getProfile().getProfileAddress());
		response.setCompanyName(user.getCompany().getCompanyName());
		response.setRoleName(user.getRole().getRoleName());
		
		if(user.getProfile().getFile() != null) {
			final FileDto fileDto = new FileDto();
			fileDto.setFiles(user.getProfile().getFile().getFiles());
			fileDto.setFileFormat(user.getProfile().getFile().getFileFormat());
		}
		
		return response;
	}

	@Transactional
	@Override
	public UpdateResDto changePassword(ChangePasswordReqDto data) {
		final UpdateResDto response = new UpdateResDto();
		final User user = userDao.getById(principalService.getId());
		
		if(passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
			user.setPassword(passwordEncoder.encode(data.getNewPassword()));
			
			final User updatedUser = em.merge(user);
			em.flush();
			response.setVer(updatedUser.getVer());
			response.setMsg("Password Changed Successfully");
		} else {
			throw new CustomException("Error! old password did not match!");
		}
		return response;
	}
}
