package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.entity.Classroom;


public class ClassroomMapper {
    public static Classroom mapToClassroom(ClassroomDto classroomDto) {
        Classroom classroom = new Classroom(
                classroomDto.getClassroomId(),

                classroomDto.getRoomNo(),
                classroomDto.getGrade()
        );
        return classroom;
    }

    public static ClassroomDto mapToClassroomDto(Classroom classroom) {
        ClassroomDto classroomDto = new ClassroomDto(
                classroom.getClassroomId(),
                classroom.getRoomNo(),
                classroom.getGrade()
        );
        return classroomDto;
    }
}
