// src/components/common/Hero.tsx
import styles from './Hero.module.css'; // Import the CSS module

const Hero = () => (
    <div className={styles.hero}>
        <h1 className={styles.h1}>Unlock Your Potential</h1>
        <p className={styles.p}>Explore courses that propel your career to the next level.</p>
        <button className={styles.ctaButton}>Browse Courses</button>
    </div>
);

export default Hero;
