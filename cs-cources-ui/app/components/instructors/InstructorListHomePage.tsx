"use client";
import React, { useEffect, useState } from 'react';
import { getAllInstructors } from '../../services/instructorService';
import InstructorCard from './InstructorCard';
import { InstructorDTO } from '../../types';
import styles from './InstructorList.module.css';

const InstructorListHomePage: React.FC = () => {
    const [instructors, setInstructors] = useState<InstructorDTO[]>([]);

    useEffect(() => {
        const fetchInstructors = async () => {
            try {
                const { data } = await getAllInstructors();
                setInstructors(data.slice(0, 3));
            } catch (error) {
                console.error("Failed to fetch instructors:", error);
            }
        };

        fetchInstructors();
    }, []);

    return (
        <div className={styles.instructorsSection}>
            <div className={styles.instructorList}>
                {instructors.map(instructor => (
                    <InstructorCard
                        key={instructor.id}
                        firstName={instructor.firstName}
                        lastName={instructor.lastName}
                        email={instructor.email}
                        dateOfBirth={new Date(instructor.dateOfBirth).toLocaleDateString()}
                        hireDate={new Date(instructor.hireDate).toLocaleDateString()}
                    />
                ))}
            </div>
        </div>
    );
};

export default InstructorListHomePage;
