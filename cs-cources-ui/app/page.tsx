import Image from "next/image";
import Header from "@/app/components/common/Header";
import Hero from "@/app/components/common/Hero";
import CourseListHomePage from "@/app/components/courses/CourseListHomePage";
import InstructorListHomePage from "@/app/components/instructors/InstructorListHomePage";



export default function Home() {
  return (
    <main>
      <Header/>
        <Hero/>
        <CourseListHomePage/>
        <InstructorListHomePage/>
      <main className="flex min-h-screen flex-col items-center justify-between p-24">
      </main>
    </main>
  );
}
