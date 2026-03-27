package np.schoolmanagementsystem.ApiUrls;

public class API {

//    its for exam Entity
    public static final String BASE_URL_EXAM = "/api/exam";
    public static final String ADD_EXAM = "/add-exam";
    public static final String GET_EXAM = "/get-id/{examId}";
    public static final String DELETE_EXAM = "/delete/{examId}";
    public static final String GET_ALL = "/get-all";


//    its for student Entity
    public static final String BASE_URL_STUDENT = "/api/students";
    public static final String REGISTER_STUDENT = "/register";
    public static final String LOGIN_STUDENT = "/login";
    public static final String UPDATE_STUDENT = "/{studentId}";
    public static final String DELETE_STUDENT = "/{id}";
    public static final String GET_ALL_STUDENT = "/get-all";
    public static final String GET_STUDENT_BY_ID = "/{studentId}";

// ITS FOR FEE ENTITY

    public static final String BASE_URL_FEE="/api/fee";
    public static final String ADD_FEE = "/add-fee";
    public static final String DELETE_FEE_BY_ID = "/delete-fee/{feeId}";
    public static final String FEE_UPDATE = "/update-fee/{feeId}";
    public static final String GET_ALL_FEE = "/get-all-fee";

//    ITS FOR STAFF ENTITY

    public static final String BASE_URL_STAFF = "/api/staffs";
    public static final String ADD_STAFF = "/add-staff";
    public static final String LOGIN_STAFF = "/login";
    public static final String DELETE_STAFF_BY_ID = "/delete-staff/{staffId}";

//    ITS FOR SUBJECT ENTITY

    public static final String BASE_URL_SUBJECT = "/api/subjects";
    public static final String ADD_SUBJECT = "/add-subject";
    public static final String GET_SUBJECT_BY_ID = "/{id}";
    public static final String UPDATE_SUBJECT_BY_ID = "/update-subject/{id}";
    public static final String DELETE_SUBJECT_BY_ID = "/delete-subject/{id}";
    public static final String GET_ALL_SUBJECT = "/get-all";


//    ITS FOR TEACHER ENTITY
    public static final String BASE_URL_TEACHER = "/api/teachers";
    public static final String REGISTER_TEACHER = "/register";
    public static final String LOGIN_TEACHER = "/login";
    public static final String DELETE_TEACHER_BY_ID = "/delete-teacher/{id}";
    public static final String GET_TEACHER_BY_ID = "/get-teacher/{id}";
    public static final String UPDATE_TEACHER_BY_ID = "/update-teacher/{id}";

//    ITS FOR CLASSROOM ENTITY
    public static final String BASE_URL_CLASSROOM = "/api/classrooms";
    public static final String ADD_CLASSROOM = "/add-classroom";
    public static final String GET_ALL_CLASSROOM = "/get-all";
    public static final String DELETE_CLASSROOM_BY_ID = "/delete-classroom/{id}";
    public static final String UPDATE_CLASSROOM_BY_ID = "/update-classroom/{id}";
    public static final String GET_CLASSROOM_BY_ID = "/get-classroom/{id}";
    public static final String GET_ALL_GRADE = "/get-grades";

}
