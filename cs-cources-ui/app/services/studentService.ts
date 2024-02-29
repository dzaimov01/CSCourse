import axios from 'axios';
import { StudentDTO, Page } from '../types';

const API_BASE_URL = 'http://localhost:8080/v1/students';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
});

export const createStudent = async (studentDTO: StudentDTO) => {
    return apiClient.post<StudentDTO>('/createUser', studentDTO);
};

export const getStudentById = async (id: string) => {
    return apiClient.get<StudentDTO>(`/${id}`);
};

export const getAllStudents = async () => {
    return apiClient.get<StudentDTO[]>(``);
};

export const getStudentsByFirstName = async (firstName: string, pageable: any) => {
    return apiClient.get<Page<StudentDTO>>(`/search/byFirstName?firstName=${firstName}`, { params: pageable });
};

export const getStudentsByLastName = async (lastName: string, pageable: any) => {
    return apiClient.get<Page<StudentDTO>>(`/search/byLastName?lastName=${lastName}`, { params: pageable });
};

export const getStudentsByEnrollmentDate = async (enrollmentDate: Date | string, pageable: any) => {
    return apiClient.get<Page<StudentDTO>>(`/search/byEnrollmentDate?enrollmentDate=${enrollmentDate}`, { params: pageable });
};

export const updateStudent = async (id: string, studentDTO: StudentDTO) => {
    return apiClient.put<StudentDTO>(`/${id}`, studentDTO);
};

export const deleteStudent = async (id: string) => {
    return apiClient.delete<void>(`/${id}`);
};
