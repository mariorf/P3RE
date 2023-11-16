package com.example.p3re.data

data class ClassroomAnswer(
    val Date: String,
    val Question: String,
    val Answer: String
)

data class ClassroomData(
    val Aprilclassroomanswers: List<ClassroomAnswer>,
    val Mayclassroomanswers: List<ClassroomAnswer>,
    val `MayMid-termexams`: List<ClassroomAnswer>,
    val Juneclassroomanswers: List<ClassroomAnswer>,
    val Julyclassroomanswers: List<ClassroomAnswer>,
    val Firstsemesterfinalexam: List<ClassroomAnswer>,
    val Septemberclassroomanswers: List<ClassroomAnswer>,
    val Octoberclassroomanswers: List<ClassroomAnswer>,
    val Secondsemestermidterms: List<ClassroomAnswer>,
    val Novemberclassroomanswers: List<ClassroomAnswer>,
    val Decemberclassroomanswers: List<ClassroomAnswer>,
    val DecemberExams: List<ClassroomAnswer>,
    val Januaryclassroomanswers: List<ClassroomAnswer>
)