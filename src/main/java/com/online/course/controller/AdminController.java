package com.online.course.controller;
 
import java.io.IOException;
import java.util.List; 
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.course.model.Course;
import com.online.course.model.User;
import com.online.course.service.CourseService; 

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    CourseService service;

 // 1.1 Add one course at a time
//    @PostMapping("/add-course")
//    public ResponseEntity<?> addCourse(@RequestBody Course course) {
//        try {
//            Course course1 = service.addCourse(course);
//            return new ResponseEntity<>(course1, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    //1.2 add course with the image
    @PostMapping("/add-course")
    public ResponseEntity<?> addCourse(@RequestPart Course course,
    									@RequestPart MultipartFile imageFile) {
        try {
            Course course1 = service.addCourse(course, imageFile);
            return new ResponseEntity<>(course1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //1.3 getting the image to show in the card(front end)
    @GetMapping("/course/{courseId}/image")
    public ResponseEntity<byte[]> getImageByCourseId(@PathVariable("courseId") Long courseId) {
	    
    	Course course = service.getCourseById(courseId);
    	byte[] imageFile = course.getImageData();
    	return ResponseEntity.ok()
    			.contentType(MediaType.valueOf(course.getImageType()))
    			.body(imageFile);
	}
    
    // 2. Add multiple courses
    @PostMapping("/add-courses")
    public ResponseEntity<?> addAllCourse(@RequestBody List<Course> courseList) {
        try {
            List<Course> courses = service.addAllCourse(courseList);
            return new ResponseEntity<>(courses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //3. Retrieve course by id
    @GetMapping("/get-course/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = service.getById(id);
        if (course != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .header("info", "Data retrieved successfully!!")
                    .body(course);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("info", "Course not found")
                    .build();
        }
    }

    
    //4. retrieve all courses
    @GetMapping("/get-courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = service.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK)
                .header("info", "Data retrieved successfully!")
                .body(courses);
    }

    
    //5.1 update course by id
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateCourse(
//            @PathVariable Long id,
//            @RequestBody Course course) {
//        
//        Optional<Course> updated = service.updateCourseById(id, course);
//        
//        if (updated.isPresent()) {
//            return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Failed to update: Course not found", HttpStatus.BAD_REQUEST);
//        }
//    }
//    
    //5.1 update the course by id with image file
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCouse(@PathVariable Long id, 
    											@RequestPart Course course,
                                                @RequestPart MultipartFile imageFile){
    	Course course1 = null;
        try {
        	course1 = service.updateCourse(id, course, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if(course1 != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    
    //6. Delete course by id
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
	    boolean deleted = service.deleteCourseById(id);
	    if (deleted) {
	        return ResponseEntity.ok("Course deleted Successfully!!");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found!!");
	    }
	}
    
  //7. retrieve/ search the course with title
    @GetMapping("/get-course")
    public ResponseEntity<List<Course>> getCourseByTitle(@RequestParam String title) {
        
        List<Course> course = service.findByTitleIgnoreCase(title);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    //8. get all users
    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getAllUser() {
    	List<User> users = service.getAllUsers();
    	
        return ResponseEntity.status(HttpStatus.OK)
        		.header("info", "Data retrieved Successfully!")
        		.body(users);
    }


    //============================================
  
 // 9. Enroll a user in a course
    @PostMapping("/enroll")
    public ResponseEntity<?> enrollUserInCourse(@RequestParam Long userId, @RequestParam Long courseId) {
    	 System.out.println("Enroll called with userId=" + userId + ", courseId=" + courseId);
        try {
            User updatedUser = service.enrollUserInCourse(userId, courseId);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    
}

