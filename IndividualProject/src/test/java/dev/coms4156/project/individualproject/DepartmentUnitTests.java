package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
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
public class DepartmentUnitTests {
  /**
   * Set up test courses before each test.
   */
  @BeforeEach
  public void setupDepartmentForTesting() {
    Map<String, Course> testCourses = new HashMap<>();
    testCourses.put("101", new Course("101 instructor", "room 101", "8:30-10:00", 100));
    testCourses.put("201", new Course("201 instructor", "room 201", "8:30-10:00", 100));
    testDepartment = new Department("000", testCourses, "test chair", 20);
  }

  @Test
  public void getNumberOfMajorsTest() {
    int expectedResult = 20;
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    String expectedResult = "test chair";
    assertEquals(expectedResult, testDepartment.getDepartmentChair());
  }

  @Test
  public void getCourseSelectionTest() {
    Map<String, Course> expectedResult = new HashMap<>();
    expectedResult.put("101", new Course("101 instructor", "room 101", "8:30-10:00", 100));
    expectedResult.put("201", new Course("201 instructor", "room 201", "8:30-10:00", 100));
    assertEquals(expectedResult.size(), testDepartment.getCourseSelection().size());
  }

  @Test
  public void addPersonToMajorTest() {
    int expectedResult = 21;
    testDepartment.addPersonToMajor();
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorTest() {
    int expectedResult = 19;
    testDepartment.dropPersonFromMajor();
    assertEquals(expectedResult, testDepartment.getNumberOfMajors());
  }

  @Test
  public void addCourseTest() {
    Course newCourse = new Course("301 instructor", "room 301", "8:30-10:00", 100);
    testDepartment.addCourse("301", newCourse);
    assertEquals(newCourse, testDepartment.getCourseSelection().get("301"));
  }

  @Test
  public void createCourseTest() {
    String expectedResult = "\nInstructor: 301 instructor; Location: room 301; Time: 8:30-10:00";
    testDepartment.createCourse("301", "301 instructor", "room 301", "8:30-10:00", 100);
    assertEquals(expectedResult, testDepartment.getCourseSelection().get("301").toString());
  }

  @Test
  public void toStringTest() {
    String expectedResult = "000 101: \n"
        + "Instructor: 101 instructor; Location: room 101; Time: 8:30-10:00\n"
        + "000 201: \n"
        + "Instructor: 201 instructor; Location: room 201; Time: 8:30-10:00\n";
    assertEquals(expectedResult, testDepartment.toString());
  }


  public static Department testDepartment;
}
