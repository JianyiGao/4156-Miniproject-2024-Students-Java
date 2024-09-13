package dev.coms4156.project.individualproject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTests {
  @Autowired
  private MockMvc mockMvc;


  @Test
  public void testIndex() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string("Welcome, in order to make an API call direct your browser or Postman to an endpoint "
            + "\n\n This can be done using the following format: \n\n http:127.0.0"
            + ".1:8080/endpoint?arg=value"));
  }

  @Test
  public void retrieveDepartmentTest() throws Exception {
   mockMvc.perform(get("/retrieveDept")
            .param("deptCode", "COMS")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void retrieveDepartmentNonexistTest() throws Exception {
    mockMvc.perform(get("/retrieveDept")
            .param("deptCode", "Nonexist")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void retrieveCourseTest() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "COMS")
            .param("courseCode", "1004")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("\n" +
            "Instructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55"));
  }

  @Test
  public void retrieveCourseNonexistDepartmentTest() throws Exception {
  mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "Nonexist")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void retrieveCourseNonexistCourseTest() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void isCourseFullTest() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "COMS")
            .param("courseCode", "1004")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("false"));
  }

  @Test
  public void isCourseFullNonexistCourseTest() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void getMajorCountFromDeptTest() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
            .param("deptCode", "COMS")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("There are: -2700 majors in the department"));
  }

  @Test
  public void getMajorCountFromDeptNonexistDeptTest() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
            .param("deptCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void idDeptChairTest() throws Exception {
    mockMvc.perform(get("/idDeptChair")
            .param("deptCode", "COMS")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Luca Carloni is the department chair."));
  }

  @Test
  public void idDeptChairNonexistDeptTest() throws Exception {
    mockMvc.perform(get("/idDeptChair")
            .param("deptCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void findCourseLocationTest() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "COMS")
            .param("courseCode", "1004")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("417 IAB is where the course is located."));
  }

  @Test
  public void findCourseLocationNonexistCourseTest() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void findCourseInstructorTest() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
            .param("deptCode", "COMS")
            .param("courseCode", "1004")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Adam Cannon is the instructor for the course."));
  }

  @Test
  public void findCourseInstructorNonexistCourseTest() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void findCourseTimeTest() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "COMS")
            .param("courseCode", "1004")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("The course meets at: 11:40-12:55"));
  }

  @Test
  public void findCourseTimeNonexistCourseTest() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void addMajorToDeptTest() throws Exception {
    mockMvc.perform(patch("/addMajorToDept")
            .param("deptCode", "ELEN")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Attribute was updated successfully"));
  }

  @Test
  public void addMajorToDeptNonexistDeptTest() throws Exception {
    mockMvc.perform(patch("/addMajorToDept")
            .param("deptCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void removeMajorFromDeptTest() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept")
            .param("deptCode", "PHYS")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Attribute was updated or is at minimum"));
  }

  @Test
  public void removeMajorFromDeptNonexistDeptTest() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept")
            .param("deptCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void dropStudentFromCourseTest() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse")
            .param("deptCode", "CHEM")
            .param("courseCode", "1403")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Student has been dropped."));
  }

  @Test
  public void dropStudentFromCourseNonexistCourseTest() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse")
            .param("deptCode", "CHEM")
            .param("courseCode", "001")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void setEnrollmentCountTest() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount")
            .param("deptCode", "COMS")
            .param("courseCode", "4156")
            .param("count", "4156")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Attributed was updated successfully."));
  }

  @Test
  public void setEnrollmentCountNonexistCourseTest() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .param("count", "4156")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void changeCourseTimeTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTime")
            .param("deptCode", "COMS")
            .param("courseCode", "4156")
            .param("time", "00:00")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Attributed was updated successfully."));
  }

  @Test
  public void changeCourseTimeNonexistCourseTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTime")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .param("time", "00:00")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void changeCourseTeacherTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher")
            .param("deptCode", "COMS")
            .param("courseCode", "4156")
            .param("teacher", "test teacher")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Attributed was updated successfully."));
  }

  @Test
  public void changeCourseTeacherNonexistCourseTest() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .param("teacher", "test teacher")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void changeCourseLocationTest() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation")
            .param("deptCode", "COMS")
            .param("courseCode", "4156")
            .param("location", "new location")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Attributed was updated successfully."));
  }

  @Test
  public void changeCourseLocationNonexistCourseTest() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation")
            .param("deptCode", "COMS")
            .param("courseCode", "001")
            .param("location", "new location")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }
}