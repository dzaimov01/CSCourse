import axios from 'axios';
import { CourseDTO, Page } from '../types';

const API_BASE_URL = 'http://localhost:8080/v1/courses';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
});

export const createCourse = (courseDTO: CourseDTO) => {
    return apiClient.post<CourseDTO>('/createCourse', courseDTO);
};

export const updateCourse = (id: string, courseDTO: CourseDTO) => {
    return apiClient.put<CourseDTO>(`/${id}`, courseDTO);
};

export const getCourseById = (id: string) => {
    return apiClient.get<CourseDTO>(`/${id}`);
};

export const getCoursesByName = (name: string, pageable: any) => {
    // Implement pageable or pagination as needed
    return apiClient.get<Page<CourseDTO>>(`/byName?name=${name}`, { params: pageable });
};

export const getCoursesByStartDate = (startDate: string, pageable: any) => {
    return apiClient.get<Page<CourseDTO>>(`/byStartDate?startDate=${startDate}`, { params: pageable });
};

export const getCoursesByEndDate = (endDate: string, pageable: any) => {
    return apiClient.get<Page<CourseDTO>>(`/byEndDate?endDate=${endDate}`, { params: pageable });
};

export const getCoursesByInstructorId = (instructorId: string, pageable: any) => {
    return apiClient.get<Page<CourseDTO>>(`/byInstructor/${instructorId}`, { params: pageable });
};

export const getAllCourses = () => {
    return apiClient.get<CourseDTO[]>(`/getAllCourses`);
};

export const deleteCourse = (id: string) => {
    return apiClient.delete<void>(`/${id}`);
};
