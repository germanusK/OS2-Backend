package Os2.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Os2.backend.service.PackageRepository;
import Os2.backend.model.Package;

@RestController
public class PackageController {
	
	@Autowired
	private PackageRepository packageRepository;
	
	

	@RequestMapping(value="packages")
	public List<Package> getAllPackages(){
		return  this.packageRepository.findAll();
	}
	
	@RequestMapping(value="/packages/{id}")
	public Optional<Package> getPackage(@PathVariable("id") int packageId){
		return this.packageRepository.findById(packageId);
	}
	
	@PostMapping(value="packages")
	public Package createPackage(@RequestBody Package _package){
		return this.packageRepository.save(_package);
	}
	
	@PutMapping(value="packages/{id}")
	public Package updatePackage(@PathVariable("id") int packageId, @RequestBody Package update){
		update.id = packageId;
		return this.packageRepository.save(update);
	}
	
	@DeleteMapping(value="packages/{id}")
	public void deletePackage(@PathVariable("id") int packageId){
		 this.packageRepository.deleteById(packageId);
	}

}
