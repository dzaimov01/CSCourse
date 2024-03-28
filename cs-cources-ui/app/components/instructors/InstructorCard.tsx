import React from 'react';
import styles from './InstructorCard.module.css';

interface InstructorCardProps {
    firstName: string;
    lastName: string;
    email: string;
    dateOfBirth: string;
    hireDate: string;
}

const InstructorCard: React.FC<InstructorCardProps> = ({
                                                           firstName,
                                                           lastName,
                                                           email,
                                                           dateOfBirth,
                                                           hireDate,
                                                       }) => (
    <div className={styles.card}>
        <div className={styles.content}>
            <h2 className={styles.name}>{`${firstName} ${lastName}`}</h2>
            <p className={styles.email}>{email}</p>
            <div className={styles.dates}>
                <p>Born: {dateOfBirth}</p>
                <p>Hire Date: {hireDate}</p>
            </div>
        </div>
    </div>
);

export default InstructorCard;
