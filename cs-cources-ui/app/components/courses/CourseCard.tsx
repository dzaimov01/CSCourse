import React from 'react';
import styles from './CourseCard.module.css';

interface CourseCardProps {
    name: string;
    description: string;
    startDate: string;
    endDate: string;
}

const CourseCard: React.FC<CourseCardProps> = ({ name, description, startDate, endDate }) => (
    <div className={styles.card}>
        <div className={styles.content}>
            <h2 className={styles.title}>{name}</h2>
            <p className={styles.description}>{description}</p>
        </div>
        <div className={styles.footer}>
            <p className={styles.dates}>Start: {startDate} | End: {endDate}</p>
            <button className={styles.button}>Enroll Now</button>
        </div>
    </div>
);

export default CourseCard;
