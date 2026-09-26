package com.example.myapplication.data.repository

import com.example.myapplication.data.model.AffairItem
import com.example.myapplication.data.model.AffairStatus
import com.example.myapplication.data.model.AttendanceStatus
import com.example.myapplication.data.model.CampusPhoto
import com.example.myapplication.data.model.CampusPhotoCategory
import com.example.myapplication.data.model.FaqItem
import com.example.myapplication.data.model.FinanceOverview
import com.example.myapplication.data.model.GradeItem
import com.example.myapplication.data.model.InstallmentItem
import com.example.myapplication.data.model.InstallmentStatus
import com.example.myapplication.data.model.PaymentState
import com.example.myapplication.data.model.ScheduleItem
import com.example.myapplication.data.model.ScheduleType
import com.example.myapplication.data.model.StudentProfile

object MockRepository {

    fun getStudentProfile(): StudentProfile {
        return StudentProfile(
            firstName = "Alex",
            lastName = "Kowalczyk",
            albumNumber = "12345",
            email = "12345@student.merito.pl",
            fieldOfStudy = "Informatyka",
            specialization = "Aplikacje Mobilne i Chmurowe",
            yearOfStudy = 2,
            semester = 4,
            status = "Student aktywny",
            studyMode = "Stacjonarne (Dzienne)",
            degree = "I stopnia (Inżynierskie)",
            faculty = "Wydział Finansów i Informatyki",
            campus = "WSB Merito Poznań",
            avatarUrl = null,
            initials = "A"
        )
    }

    fun getScheduleItems(): List<ScheduleItem> {
        return listOf(
            ScheduleItem(
                id = "1",
                title = "Programowanie obiektowe",
                type = ScheduleType.LABORATORIUM,
                room = "Lab 204 - Budynek B",
                instructor = "dr inż. Marek Nowak",
                startTime = "08:15",
                endTime = "11:30",
                date = "2025-03-15",
                dayOfWeek = "Sobota",
                attendanceStatus = AttendanceStatus.PRESENT
            ),
            ScheduleItem(
                id = "2",
                title = "Bazy danych i SQL",
                type = ScheduleType.WYKLAD,
                room = "Aula A1 - Budynek Główny",
                instructor = "prof. dr hab. Ewa Kowalska",
                startTime = "11:45",
                endTime = "13:15",
                date = "2025-03-15",
                dayOfWeek = "Sobota",
                attendanceStatus = AttendanceStatus.PRESENT
            ),
            ScheduleItem(
                id = "3",
                title = "Sieci komputerowe i bezpieczeństwo",
                type = ScheduleType.CWICZENIA,
                room = "Sala 310 - Budynek C",
                instructor = "mgr inż. Tomasz Wiśniewski",
                startTime = "13:30",
                endTime = "15:00",
                date = "2025-03-15",
                dayOfWeek = "Sobota",
                attendanceStatus = AttendanceStatus.PENDING
            ),
            ScheduleItem(
                id = "4",
                title = "Tworzenie Aplikacji Mobilnych",
                type = ScheduleType.PROJEKT,
                room = "Lab 108 - Budynek B",
                instructor = "dr Piotr Zieliński",
                startTime = "15:15",
                endTime = "18:30",
                date = "2025-03-16",
                dayOfWeek = "Niedziela",
                attendanceStatus = AttendanceStatus.PENDING
            ),
            ScheduleItem(
                id = "5",
                title = "Inżynieria oprogramowania",
                type = ScheduleType.WYKLAD,
                room = "Aula A2 - Budynek Główny",
                instructor = "dr hab. Anna Lewandowska",
                startTime = "08:15",
                endTime = "09:45",
                date = "2025-03-16",
                dayOfWeek = "Niedziela",
                attendanceStatus = AttendanceStatus.EXCUSED
            )
        )
    }

    fun getFinanceOverview(): FinanceOverview {
        return FinanceOverview(
            totalToPay = 2400.0,
            dueDate = "15.11.2025",
            remainingInstallmentsCount = 3,
            paymentState = PaymentState.PENDING,
            accountNumber = "PL89 1090 1362 0000 0001 2345 6789",
            installments = listOf(
                InstallmentItem(
                    id = "inst_1",
                    name = "Rata 1/4 - Czesne Semestr Zimowy",
                    status = InstallmentStatus.PAID,
                    amount = 600.0,
                    dueDate = "15.10.2025"
                ),
                InstallmentItem(
                    id = "inst_2",
                    name = "Rata 2/4 - Czesne Semestr Zimowy",
                    status = InstallmentStatus.PENDING,
                    amount = 600.0,
                    dueDate = "15.11.2025"
                ),
                InstallmentItem(
                    id = "inst_3",
                    name = "Rata 3/4 - Czesne Semestr Zimowy",
                    status = InstallmentStatus.PLANNED,
                    amount = 600.0,
                    dueDate = "15.12.2025"
                ),
                InstallmentItem(
                    id = "inst_4",
                    name = "Rata 4/4 - Czesne Semestr Zimowy",
                    status = InstallmentStatus.PLANNED,
                    amount = 600.0,
                    dueDate = "15.01.2026"
                ),
                InstallmentItem(
                    id = "inst_5",
                    name = "Opłata za wydanie legitymacji ELS",
                    status = InstallmentStatus.PAID,
                    amount = 22.0,
                    dueDate = "01.10.2025"
                )
            )
        )
    }

    fun getAffairItems(): List<AffairItem> {
        return listOf(
            AffairItem(
                id = "aff_1",
                caseNumber = "#WSB-8921",
                category = "Stypendia",
                title = "Wniosek o stypendium socjalne",
                status = AffairStatus.IN_PROGRESS,
                submissionDate = "12.10.2025 14:30",
                timeAgo = "2 godziny temu",
                description = "Wniosek złożony elektronicznie wraz z kompletem załączników o dochodach gospodarstwa domowego."
            ),
            AffairItem(
                id = "aff_2",
                caseNumber = "#WSB-7712",
                category = "Legitymacje",
                title = "Przedłużenie ważności legitymacji ELS",
                status = AffairStatus.APPROVED,
                submissionDate = "01.10.2025 09:15",
                timeAgo = "1 miesiąc temu",
                description = "Hologram na semestr zimowy jest gotowy do odbioru w dziekanacie."
            ),
            AffairItem(
                id = "aff_3",
                caseNumber = "#WSB-6501",
                category = "Podania",
                title = "Prośba o rozłożenie czesnego na raty",
                status = AffairStatus.APPROVED,
                submissionDate = "20.09.2025 11:00",
                timeAgo = "2 miesiące temu",
                description = "Harmonogram ratalny został zatwierdzony przez Kwesturę."
            ),
            AffairItem(
                id = "aff_4",
                caseNumber = "#WSB-5420",
                category = "Organizacja studiów",
                title = "Wniosek o Indywidualną Organizację Studiów (IOS)",
                status = AffairStatus.APPROVED,
                submissionDate = "05.09.2025 16:45",
                timeAgo = "2 miesiące temu",
                description = "Wniosek zaakceptowany przez Dziekana Wydziału."
            ),
            AffairItem(
                id = "aff_5",
                caseNumber = "#WSB-4110",
                category = "Zaświadczenia",
                title = "Zaświadczenie o statusie studenta do ZUS",
                status = AffairStatus.APPROVED,
                submissionDate = "01.09.2025 10:00",
                timeAgo = "2 miesiące temu",
                description = "Dokument w formie elektronicznej został wygenerowany."
            )
        )
    }

    fun getFaqItems(): List<FaqItem> {
        return listOf(
            FaqItem(
                id = "faq_1",
                category = "Akademiki",
                question = "Jak złożyć wniosek o akademik?",
                answer = "Wniosek o akademik składa się elektronicznie w zakładce Sprawy -> Złóż nowe podanie -> Wniosek o przyznanie miejsca w domu studenckim przed rozpoczęciem semestru."
            ),
            FaqItem(
                id = "faq_2",
                category = "Oceny & Egzaminy",
                question = "Kiedy są wyniki egzaminów?",
                answer = "Wyniki egzaminów i zaliczeń publikowane są w e-Uczelni oraz w aplikacji w zakładce Profil -> Wyniki i oceny w ciągu 7 dni roboczych od daty przeprowadzenia egzaminu."
            ),
            FaqItem(
                id = "faq_3",
                category = "Dokumenty",
                question = "Jak uzyskać zaświadczenie studenta?",
                answer = "Zaświadczenie o statusie studenta z kodem QR i cyfrowym podpisem możesz pobrać natychmiast w zakładce Profil -> Moje dokumenty lub zamówić wersję papierową w Dziekanacie."
            ),
            FaqItem(
                id = "faq_4",
                category = "Dziekanat",
                question = "Jakie są godziny otwarcia Dziekanatu?",
                answer = "Dziekanat jest otwarty od wtorku do piątku w godz. 9:00 - 14:00 oraz w dojazdowe soboty i niedziele w godz. 8:00 - 15:00."
            ),
            FaqItem(
                id = "faq_5",
                category = "Płatności",
                question = "Gdzie znajdę indywidualny numer konta do wpłat?",
                answer = "Indywidualny numer rachunku jest widoczny w zakładce Finanse w aplikacji oraz w portalu e-Uczelnia."
            ),
            FaqItem(
                id = "faq_6",
                category = "Stypendia",
                question = "Kiedy mija termin składania wniosków stypendialnych?",
                answer = "Wnioski o stypendium rektora oraz socjalne należy składać do 15 października na semestr zimowy oraz do 15 marca na semestr letni."
            )
        )
    }

    fun getCampusPhotos(): List<CampusPhoto> {
        return listOf(
            CampusPhoto(
                id = "cam_1",
                title = "Budynek Główny WSB Merito",
                description = "Główny budynek uczelni WSB Merito. Mieszczą się w nim Rektorat, Dziekanat, Biuro Karier oraz reprezentacyjna Aula Główna.",
                category = CampusPhotoCategory.BUILDING,
                imageUrl = "https://images.unsplash.com/photo-1562774053-701939374585?w=800"
            ),
            CampusPhoto(
                id = "cam_2",
                title = "Sala Dydaktyczna / Wykładowa A1",
                description = "Nowoczesna, przestronna sala wykładowa z udogodnieniami multimedialnymi, nagłośnieniem i dostępem do WiFi.",
                category = CampusPhotoCategory.AULA,
                imageUrl = "https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?w=800"
            ),
            CampusPhoto(
                id = "cam_3",
                title = "Biblioteka Akademicka",
                description = "Otwarta strefa nauki z bogatym księgozbiorem, stanowiskami z dostępem do baz naukowych oraz strefami cichej pracy.",
                category = CampusPhotoCategory.LIBRARY,
                imageUrl = "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?w=800"
            ),
            CampusPhoto(
                id = "cam_4",
                title = "Strefa Studenta & Chillout Zone",
                description = "Strefa wypoczynku i integracji studenckiej wyposażona w kanapy, stacje ładowania urządzeń, kawiarnię i miejsca do pracy grupowej.",
                category = CampusPhotoCategory.STUDENT_ZONE,
                imageUrl = "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=800"
            ),
            CampusPhoto(
                id = "cam_5",
                title = "Laboratorium Komputerowe i AI",
                description = "Pracownia specjalistyczna wyposażona w wydajne stacje robocze, najnowsze oprogramowanie oraz urządzenia testowe iOS/Android.",
                category = CampusPhotoCategory.LAB,
                imageUrl = "https://images.unsplash.com/photo-1531482615713-2afd69097998?w=800"
            )
        )
    }

    fun getGrades(): List<GradeItem> {
        return listOf(
            GradeItem(
                id = "g1",
                subjectName = "Programowanie obiektowe",
                grade = 4.5,
                ects = 5,
                date = "28.01.2025",
                instructor = "dr inż. Marek Nowak",
                type = "Egzamin"
            ),
            GradeItem(
                id = "g2",
                subjectName = "Bazy danych i SQL",
                grade = 5.0,
                ects = 6,
                date = "02.02.2025",
                instructor = "prof. dr hab. Ewa Kowalska",
                type = "Zaliczenie z oceną"
            ),
            GradeItem(
                id = "g3",
                subjectName = "Sieci komputerowe",
                grade = 4.0,
                ects = 4,
                date = "25.01.2025",
                instructor = "mgr inż. Tomasz Wiśniewski",
                type = "Egzamin"
            ),
            GradeItem(
                id = "g4",
                subjectName = "Inżynieria oprogramowania",
                grade = 4.5,
                ects = 5,
                date = "30.01.2025",
                instructor = "dr hab. Anna Lewandowska",
                type = "Projekt"
            ),
            GradeItem(
                id = "g5",
                subjectName = "Tworzenie Aplikacji Mobilnych",
                grade = 5.0,
                ects = 6,
                date = "05.02.2025",
                instructor = "dr Piotr Zieliński",
                type = "Projekt"
            )
        )
    }
}
