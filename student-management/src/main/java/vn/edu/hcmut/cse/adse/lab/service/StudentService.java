package vn.edu.hcmut.cse.adse.lab.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public List<Student> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword);
    }

    public Student create(Student student) {
        student.setId(UUID.randomUUID());
        return repository.save(student);
    }

    public Student update(UUID id, Student updated) {
        Student existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());
        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
