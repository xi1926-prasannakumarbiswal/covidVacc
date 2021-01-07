package com.covid.vacc.controller;

import com.covid.vacc.dto.Branch;
import com.covid.vacc.exceptions.ResourceNotFoundException;
import com.covid.vacc.model.CVNResponse;
import com.covid.vacc.service.AvailabilityService;
import com.google.api.client.http.HttpStatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/availability")
@CrossOrigin(origins = "*")
public class AvailabilityResource {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping()
    public @ResponseBody
    ResponseEntity<CVNResponse> getPaginatedAndAllBranches(@RequestParam(name = "pageNumber",defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize,
                                                           @RequestParam(name = "sortBy",defaultValue = "modifiedAt") String sortBy,
                                                           @RequestParam(name = "order", defaultValue = "desc") String order) throws ResourceNotFoundException {
        log.info("Request received to get all branches");
        List<Branch> branches = availabilityService.getAllBranch(pageNumber,pageSize,sortBy,order);
        log.info("Number of branches fetched: ", branches.size());
        return new ResponseEntity<>(new CVNResponse(HttpStatusCodes.STATUS_CODE_OK, "Branches fetched from db", branches), HttpStatus.OK);

    }

    @GetMapping("/{branchId}")
    public @ResponseBody ResponseEntity<CVNResponse> getAvailableVaccinesPerBranchId(@Valid @PathVariable final Long branchId) throws ResourceNotFoundException {
        log.info("Request received to get Available Vaccines Branch with BranchId :",branchId);
        List<Branch> availableVaccinesPerBranch = availabilityService.getAvailableVaccinesPerBranch(branchId);
        log.info("Number of  available vaccines fetched per branch fetched",availableVaccinesPerBranch.size());
        return new ResponseEntity<>(new CVNResponse(HttpStatusCodes.STATUS_CODE_OK,"Available Vaccines per branch fetched from db",availableVaccinesPerBranch), HttpStatus.OK);
    }

    @GetMapping("/branch")
    public @ResponseBody ResponseEntity<CVNResponse> getSpecificAvailableVaccinesByBranch(@RequestParam("branchId") final Long branchId,
                                                                                    @RequestParam("vaccineId") final Long vaccineId) throws ResourceNotFoundException {
        log.info("Request received to Get a specific availability by branch :",branchId + "and vaccineId: "+ vaccineId);
        Branch  specificVaccineAvailabilityByBranch = availabilityService.getSpecificVaccineAvailabilityByBranch(branchId, vaccineId);
        log.info("Branch with branchId - fetched", branchId + "and vaccineId: " +vaccineId);
        return new ResponseEntity<>(new CVNResponse(HttpStatusCodes.STATUS_CODE_OK,"Specific vaccine availability by branch fetched from db",specificVaccineAvailabilityByBranch), HttpStatus.OK);
    }

    @GetMapping("/slot")
    public @ResponseBody ResponseEntity<CVNResponse> getAvailableTimeForABranch(@RequestParam("branchId") final Long branchId) throws ResourceNotFoundException {
        log.info("Request received to Get available time for a branch :",branchId );
        Branch  specificVaccineAvailabilityByBranch = availabilityService.getAvailableTimeForABranchId(branchId);
        log.info("Branch with branchId - fetched", branchId);
        return new ResponseEntity<>(new CVNResponse(HttpStatusCodes.STATUS_CODE_OK,"Specific vaccine availability by branch fetched from db",specificVaccineAvailabilityByBranch), HttpStatus.OK);
    }



}
