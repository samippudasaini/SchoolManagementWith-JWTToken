package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Student;


public class ClassroomMapper {
    public static Classroom mapToClassroom(ClassroomDto classroomDto) {
        Classroom classroom = new Classroom(
                classroomDto.getId(),
                classroomDto.getRoom_No(),
                classroomDto.getGrade()
        );
        return classroom;
    }
    public static ClassroomDto mapToClassroomDto(Classroom classroom) {
        ClassroomDto classroomDto = new ClassroomDto(
                classroom.getId(),
                classroom.getRoom_No(),
                classroom.getGrade()
        );
        return classroomDto;
    }
}
