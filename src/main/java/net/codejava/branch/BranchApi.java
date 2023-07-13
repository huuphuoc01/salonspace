package net.codejava.branch;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/branch")
@CrossOrigin(origins = "*")
public class BranchApi {

    @Autowired BranchReponsitory branchRepo;

    @PostMapping
    public ResponseEntity<BranchEnity> create(@RequestBody @Valid BranchEnity branch) {
        BranchEnity savedSBranch = branchRepo.save(branch);
        URI productURI = URI.create("/branch/" + savedSBranch.getId());
        return ResponseEntity.created(productURI).body(savedSBranch);
    }

    @GetMapping
    public List<BranchEnity> list() {
        return branchRepo.findAll();
    }

    @GetMapping("/detail/{id}")
    public BranchEnity listId(@PathVariable Integer id) {
        return branchRepo.getById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody @Valid BranchEnity branch){
        if ( branchRepo.existsById(id)==false){
            return ResponseEntity.ok("ID không tồn tại");
        }else {
            BranchEnity update = branchRepo.getById(id);

            update.setAddress(branch.getAddress());
            update.setName(branch.getName());
            update.setLat(branch.getLat());
            update.setLng(branch.getLng());
            branchRepo.save(update);
            return ResponseEntity.ok(update);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if ( branchRepo.existsById(id)==false){
            return ResponseEntity.ok("ID không tồn tại");
        }else {
            branchRepo.deleteById(id);
            return ResponseEntity.ok(id);
        }
    }
}
