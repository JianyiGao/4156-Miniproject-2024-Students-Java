package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit test for Course.
 *
 * <p>
 *   Verifies all methods in Course
 * </p>
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void enrollStudentTest() {
    boolean expectedResult = false;
    assertEquals(expectedResult, testCourse.enrollStudent());
    assertEquals(500, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void dropStudentTest() {
    boolean expectedResult = true;
    assertEquals(expectedResult, testCourse.dropStudent());
    assertEquals(499, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void getCourseLocationTest() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    String expectedResult = "Griffin Newbold";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    String expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void reassignInstructorTest() {
    String expectedResult = "new instructor";
    testCourse.reassignInstructor("new instructor");
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void reassignLocationTest() {
    String expectedResult = "new location";
    testCourse.reassignLocation("new location");
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void reassignTimeTest() {
    String expectedResult = "new time";
    testCourse.reassignTime("new time");
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void setEnrolledStudentCountTest() {
    int expectedResult = 100;
    testCourse.setEnrolledStudentCount(100);
    assertEquals(expectedResult, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void getEnrolledStudentCountTest() {
    int expectedResult = 500;
    assertEquals(expectedResult, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void isCourseFullTest() {
    boolean expectedResult = true;
    assertEquals(expectedResult, testCourse.isCourseFull());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

