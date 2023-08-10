package com.lawencon.ticketjosep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.ticketjosep.model.File;

@Repository
public interface FileRepo extends JpaRepository<File, Long>{
	
	@Modifying
	@Query(value = "DELETE "
			+ "FROM "
			+ "t_file "
			+ "WHERE id = ?1", nativeQuery = true)
	int removeFile(Long id);
}
