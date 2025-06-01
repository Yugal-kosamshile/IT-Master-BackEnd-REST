package com.online.course.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.course.model.Course;
import com.online.course.model.User;
import com.online.course.repository.CourseRepository;
import com.online.course.repository.UserRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository repo;
	
	@Autowired
	UserRepository userRepo;
	 
	//1. to add one course
//	public Course addCourse(Course course ) throws IOException{ 
//		return repo.save(course) ;
//		
//	}
	
	public Course addCourse(Course course, MultipartFile imageFile ) throws IOException { 
		
		course.setImageName(imageFile.getOriginalFilename());
		course.setImageType(imageFile.getContentType());
		course.setImageData(imageFile.getBytes());
		return repo.save(course) ;
		
	}
	
	//2. get the list of all courses
		public List<Course> addAllCourse(List<Course> courses) {
		    return repo.saveAll(courses);
		}
		
	//3. to retrieve/ get all course
	public List<Course> getAllCourses() {
		return repo.findAll();
	}
	
	//4. get one course by Id
	public Course getById(Long id) {
		return repo.findById(id).orElse(null);
	}

//	//5.update the course by id
//	public Optional<Course> updateCourseById(Long id, Course updatedCourse) {
//		Optional<Course> course = repo.findById(id);
//		if(course.isPresent()) {
//			Course existingCourse = course.get();
//			existingCourse.setTitle(updatedCourse.getTitle());
//			existingCourse.setSubtitle(updatedCourse.getSubtitle());
//			existingCourse.setShort_description(updatedCourse.getShort_description());
//			existingCourse.setCreated_by(updatedCourse.getCreated_by());
//			existingCourse.setLanguage(updatedCourse.getLanguage());
//			existingCourse.setRating(updatedCourse.getRating());
//			existingCourse.setEnd_date(updatedCourse.getEnd_date());
//			existingCourse.setStart_date(updatedCourse.getStart_date());
//			existingCourse.setStudents(updatedCourse.getStudents());
//			
//			repo.save(existingCourse);
//			return Optional.of(existingCourse);
//
//		}
//		return course;
//	}

	//5.2 update the course by id with image
	public Course updateCourse(Long id, Course course, MultipartFile imageFile) throws IOException{
		course.setImageData(imageFile.getBytes());
		course.setImageName(imageFile.getOriginalFilename());
		course.setImageType(imageFile.getContentType());
        return repo.save(course);
	}
	
	
	//6. delete the course by id is exists
	public boolean deleteCourseById(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}

	//serach the course by title
	public List<Course> findByTitleIgnoreCase(String title) {
	    return repo.findByTitleContainingIgnoreCase(title);
	}

	//search the course by id
	public Course getCourseById(Long id) {
		// TODO Auto-generated method stub
		 return repo.findById(id).orElse(null);
	}

	//get teh list of users
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
//======================
	//enroll the user in a course
//	public User enrollUserInCourse(Long userId, Long courseId) {
//	    User user = userRepo.findById(userId)
//	        .orElseThrow(() -> new RuntimeException("User not found"));
//
//	    Course course = repo.findById(courseId)
//	        .orElseThrow(() -> new RuntimeException("Course not found"));
//
//	    user.getCourses().add(course);  
//	    return userRepo.save(user);  
//	}
//	
	public User enrollUserInCourse(Long userId, Long courseId) {
	    System.out.println("Service: enrolling user " + userId + " in course " + courseId);

	    User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
	    Course course = repo.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

	    // Check if user already enrolled
	    if (user.getCourses().contains(course)) {
	        throw new RuntimeException("User already enrolled in this course");
	    }

	    user.getCourses().add(course);
	    course.setStudents(course.getStudents() + 1);

	    userRepo.save(user);
repo.save(course);

	    return user;
	}


}
