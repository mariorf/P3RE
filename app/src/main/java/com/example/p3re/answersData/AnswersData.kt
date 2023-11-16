package com.example.p3re.answersData

data class AnswersData(
    val April classroom answers: List<AprilClassroomAnswer>,
    val December Exams: List<DecemberExam>,
    val December classroom answers: List<DecemberClassroomAnswer>,
    val First semester final exam: List<FirstSemesterFinalExam>,
    val January classroom answers: List<JanuaryClassroomAnswer>,
    val July classroom answers: List<JulyClassroomAnswer>,
    val June classroom answers: List<JuneClassroomAnswer>,
    val May Mid-term exams: List<MayMidTermExam>,
    val May classroom answers: List<MayClassroomAnswer>,
    val November classroom answers: List<NovemberClassroomAnswer>,
    val October classroom answers: List<OctoberClassroomAnswer>,
    val Second semester midterms: List<SecondSemesterMidterm>,
    val September classroom answers: List<SeptemberClassroomAnswer>
)