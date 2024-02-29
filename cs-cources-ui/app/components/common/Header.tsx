
import Link from 'next/link';
import styles from './Header.module.css';

const Header = () => (
    <header className={styles.header}>
        <div className={styles.logo}>
            <Link href="/">
                <p>CS Courses</p>
            </Link>
        </div>
        <nav className={styles.navigation}>
            <Link href="/courses"><p>Courses</p></Link>
            <Link href="/instructors"><p>Instructors</p></Link>
            <Link href="/students"><p>Students</p></Link>
            <Link href="/auth/login"><p>Login</p></Link>
            <Link href="/auth/register"><p>Register</p></Link>
        </nav>
    </header>
);

export default Header;

