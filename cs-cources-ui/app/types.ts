export interface CourseDTO {
    id?: string;
    name: string;
    description: string;
    startDate: Date | string;
    endDate: Date | string;
    instructorId: string;
    students?: StudentDTO[];
}

export interface StudentDTO {
    id?: string;
    firstName: string;
    lastName: string;
    email: string;
    username: string;
    password: string;
    dateOfBirth: Date | string;
    enrollmentDate: Date | string;
    courseIds?: string[];
}

export interface InstructorDTO {
    id?: string;
    firstName: string;
    lastName: string;
    email: string;
    username: string;
    password: string;
    dateOfBirth: Date | string;
    hireDate: Date | string;
    courseIds?: string[];
}


export interface Page<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    last: boolean;
    size: number;
    number: number;
    numberOfElements: number;
    first: boolean;
    empty: boolean;
}

