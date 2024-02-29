import axios from 'axios';


const API_BASE_URL = 'http://localhost:8080';

interface AuthRequest {
    username: string;
    password: string;
}

interface JwtResponse {
    accessToken: string;
}

interface RegistrationResponse extends AuthRequest {}

export const login = async (authRequest: AuthRequest): Promise<JwtResponse> => {
    try {
        const response = await axios.post(`${API_BASE_URL}/api/v1/login`, authRequest);
        return response.data;
    } catch (error) {
        throw new Error('Failed to login');
    }
};

// Function to register a new student
export const registerStudent = async (authRequest: AuthRequest): Promise<RegistrationResponse> => {
    try {
        const response = await axios.post(`${API_BASE_URL}/api/v1/student/register`, authRequest);
        return response.data;
    } catch (error) {
        throw new Error('Failed to register user');
    }
};
