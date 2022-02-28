package Os2.backend.service;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Os2.backend.model.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
	
//	create subscription package
//	Optional<ResponseEntity<Package>> create(Package _package);
	
//	get all packages
//	Optional<ResponseEntity<List<Package>>> getAll();
	
//	get sorted package list by name
//	Optional<ResponseEntity<List<Package>>> getAllSorted(String order); //order is ASC or DESC
	
//	get package by id
//	Optional<ResponseEntity<Package>> getById(long id);
	
//	get package by label/name
//	Optional<ResponseEntity<Package>> getByName(String label);
	
//	update package
//	Optional<ResponseEntity<Package>> update(long id, Package update);
	
//	delete package
//	Optional<ResponseEntity<Package>> delete(long id);

}
