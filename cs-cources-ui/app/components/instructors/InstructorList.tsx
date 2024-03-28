// Assuming this file is placed in `app/components/instructors/InstructorList.tsx`
"use client";
import React, { useEffect, useState } from 'react';
import { getAllInstructors } from '../../services/instructorService'; // Adjust the import path as needed
import InstructorCard from './InstructorCard'; // Adjust the import path as needed
import { InstructorDTO } from '../../types'; // Adjust the import path as needed
import styles from './InstructorList.module.css'; // Make sure to create and style accordingly

const InstructorList: React.FC = () => {
    const [instructors, setInstructors] = useState<InstructorDTO[]>([]);

    useEffect(() => {
        const fetchInstructors = async () => {
            try {
                const { data } = await getAllInstructors();
                setInstructors(data); // Assuming the response is directly the data
            } catch (error) {
                console.error("Failed to fetch instructors:", error);
            }
        };

        fetchInstructors();
    }, []);

    return (
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
    );
};

export default InstructorList;
