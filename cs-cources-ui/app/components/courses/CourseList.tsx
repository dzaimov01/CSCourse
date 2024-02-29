"use client";
import React, { useEffect, useState } from 'react';
import { getAllCourses } from '../../services/courseService';
import CourseCard from './CourseCard';
import { CourseDTO } from '../../types';
import styles from './CourseList.module.css';

const CourseList: React.FC = () => {
    const [courses, setCourses] = useState<CourseDTO[]>([]);

    useEffect(() => {
        const fetchCourses = async () => {
            try {
                const response = await getAllCourses();
                setCourses(response.data);
            } catch (error) {
                console.error("Failed to fetch courses:", error);
            }
        };

        fetchCourses();
    }, []);

    return (
        <div className={styles.courseList}>
            {courses.map(course => (
                <CourseCard
                    key={course.id}
                    name={course.name}
                    description={course.description}
                    startDate={new Date(course.startDate).toLocaleDateString()}
                    endDate={new Date(course.endDate).toLocaleDateString()}
                />
            ))}
        </div>
    );
};

export default CourseList;
