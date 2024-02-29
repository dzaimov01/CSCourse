import axios from 'axios';
import { InstructorDTO, Page } from '../types';

const API_BASE_URL = 'http://localhost:8080/v1/instructors';

const apiClient = axios.create({
    baseURL: API_BASE_URL,
});

export const createInstructor = async (instructorDTO: InstructorDTO) => {
    return apiClient.post<InstructorDTO>('/createUser', instructorDTO);
};

export const getInstructorById = async (id: string) => {
    return apiClient.get<InstructorDTO>(`/${id}`);
};

export const getAllInstructors = async () => {
    return apiClient.get<InstructorDTO[]>(``);
};

export const findByFirstName = async (firstName: string, pageable: any) => {
    return apiClient.get<Page<InstructorDTO>>(`/search/byFirstName?firstName=${firstName}`, { params: pageable });
};

export const findByLastName = async (lastName: string, pageable: any) => {
    return apiClient.get<Page<InstructorDTO>>(`/search/byLastName?lastName=${lastName}`, { params: pageable });
};

export const findByDateOfBirth = async (dateOfBirth: Date | string, pageable: any) => {
    return apiClient.get<Page<InstructorDTO>>(`/search/byDateOfBirth?dateOfBirth=${dateOfBirth}`, { params: pageable });
};

export const findByHireDate = async (hireDate: Date | string, pageable: any) => {
    return apiClient.get<Page<InstructorDTO>>(`/search/byHireDate?hireDate=${hireDate}`, { params: pageable });
};

export const updateInstructor = async (id: string, instructorDTO: InstructorDTO) => {
    return apiClient.put<InstructorDTO>(`/${id}`, instructorDTO);
};

export const deleteInstructor = async (id: string) => {
    return apiClient.delete<void>(`/${id}`);
};
