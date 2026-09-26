# Project Plan

Aplikacja mobilna MyMerito dla studentów Uniwersytetu WSB Merito, zaprojektowana ściśle na podstawie dostarczonych makiety graficznych.

Struktura nawigacyjna i ekrany (minimum 5 ekranów + ekran logowania):
1. **Ekran Logowania (Login)**:
   - Formularz logowania (Email / nr albumu i hasło), przycisk "Zaloguj się", branding MyMerito.

2. **Ekran Główny (Home)**:
   - Nagłówek: "Dzień dobry 👋 Cześć, Alex!", avatar "A", powiadomienia z czerwoną kropką.
   - Karta "CO TERAZ?": Ciemnoniebieski kontener, odliczanie "Za 25 min", nazwa zajęć ("Programowanie obiektowe"), sala ("Sala A-210"), prowadzący ("dr hab. M. Kowalski"), godziny ("08:15 - 09:45"), przycisk "Szczegóły >".
   - Sekcja "SZYBKI DOSTĘP": Kolorowe ikony skrótów (Plan, Oceny, Opłaty, Zgłoszenia).
   - Sekcja "Zadania i Terminy": Lista zadań z checkboxami i statusami (np. "Projekt zaliczeniowy - Bazy danych" - Pilne).
   - Wskaźniki akademickie: 3 karty metryk (Frekwencja: 86%, Avg. ocen: 4.2, Do opłaty: 1 200 zł).

3. **Plan zajęć (Plan)**:
   - Nagłówek: "Plan zajęć", "Semestr zimowy 2024/25".
   - Pasek wyboru dni tygodnia (np. Sb 19 wybrany).
   - Filtry: "Wszystkie zajęcia", "Ćwiczenia / lab".
   - Os czasu zajęć z kolorowymi badge'ami typów zajęć (Wykład, Laboratorium, Ćwiczenia), salami, prowadzącymi i potwierdzeniem obecności.
   - Przycisk pływający "Synchronizuj kalendarz".

4. **Finanse (Finanse)**:
   - Nagłówek: "Finanse - Rok akademicki 2024/25".
   - Karta podsumowania: Ciemnogranatowa karta "ŁĄCZNA KWOTA DO OPŁACENIA 2 400,00 zł", termin, pozostałe raty, pasek postępu ("Postęp opłat 3/5 rat").
   - Zakładki: "Do opłacenia" / "Historia wpłat".
   - Lista rat: Czesne - Rata 3/5 (Oczekuje), Rata 4/5 (Zaplanowana) z przyciskami "Zapłać >".
   - Przycisk "Zapłać przez BLIK" oraz link "Pobierz fakturę / dane do przelewu".

5. **Sprawy i Dziekanat (Sprawy)**:
   - Nagłówek: "Dziekanat - Sprawy, wnioski i pomoc".
   - Wyszukiwarka "Szukaj spraw, wniosków lub FAQ...".
   - Liczniki spraw: "1 W toku" (Oczekuje na decyzję), "4 Zakończone".
   - Aktywne sprawy: Karta wniosku (np. #WSB-8921 Wniosek o stypendium socjalne, status "W trakcie weryfikacji").
   - Często zadawane pytania (FAQ) w formie listy rozwijanej/przycisków.
   - Przycisk akcji "+ Złóż nowe podanie".

6. **Profil Studenta oraz Galeria Kampusu (Profil & Kampus)**:
   - Nagłówek: "Profil".
   - Karta studenta: Avatar "A", Alex Kowalczyk, Nr albumu: 12345, Informatyka, II rok, status "Student aktywny".
   - Sekcje menu: "Moje dokumenty", "Wyniki i oceny", "Powiadomienia", "Kampus (Mapa, plan budynków & Galeria zdjęć uczelni)".
   - Sekcja Kampus / Galeria zawiera minimum 3 zdjęcia akademickie (budynki uczelni WSB Merito, sale dydaktyczne, biblioteka, strefa studenta).
   - Przycisk "Wyloguj się".

7. **Nawigacja dolna (Bottom Navigation)**:
   - 5 zakładek: Home, Plan, Finanse, Sprawy, Profil z dopasowanym podświetleniem aktywnego ekranu.

## Project Brief

# MyMerito — Project Brief (MVP)

Aplikacja mobilna **MyMerito** przeznaczona dla studentów Uniwersytetu WSB Merito, oferująca spersonalizowany pulpit nawigacyjny, interaktywny plan zajęć, zarządzanie finansami oraz cyfrowy dziekanat.

---

## Features

1. **Authentication & Student Profile (Logowanie i Profil)**
   - Bezpieczne logowanie (email/nr albumu) z dostępem do profilu studenta, cyfrowego identyfikatora oraz informacji/galerii kampusu.
2. **Home Dashboard (Pulpit Główny)**
   - Dynamiczny podgląd „CO TERAZ?” z odliczaniem do najbliższych zajęć, kluczowe wskaźniki akademickie (frekwencja, średnia, opłaty), nadchodzące zadania oraz szybki dostęp do głównych modułów.
3. **Class Schedule (Plan Zajęć)**
   - Harmonogram zajęć z wyborem dni tygodnia, filtrowaniem po typie zajęć (wykład/ćwiczenia/lab), szczegółami sal i wykładowców oraz opcją synchronizacji z kalendarzem.
4. **Financial Management (Finanse)**
   - Podsumowanie statusu opłat czesnego, zestawienie rat i terminów, możliwość szybkiej płatności BLIK oraz pobieranie faktur/danych do przelewu.
5. **Student Affairs & FAQ (Sprawy i Dziekanat)**
   - Wyszukiwarka i baza FAQ, śledzenie statusu złożonych wniosków i podań z opcją tworzenia nowych zgłoszeń.

---

## High-Level Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material Design 3)
- **Navigation Strategy**: Jetpack Navigation 3 (State-Driven Navigation)
- **Adaptive Strategy**: Compose Material Adaptive Library (`androidx.compose.material3.adaptive`)
- **State & Concurrency**: Kotlin Coroutines, Flow, ViewModel, `StateFlow`
- **Architecture**: MVVM (Model-View-ViewModel) z podziałem na warstwę prezentacji oraz danych w pamięci (Mock Repository)

## Implementation Steps
**Total Duration:** 24m 34s

### Task_1_Setup_Navigation_Models_Login: Set up app data models, mock repositories, navigation infrastructure, and build Login Screen.
- **Status:** COMPLETED
- **Updates:** Model danych, repository z mockami, nawigacja (NavDisplay) oraz ekran logowania zostały pomyślnie zrealizowane. Build oraz testy przechodzą pomyślnie.
- **Acceptance Criteria:**
  - Data models and mock repositories created
  - Navigation structure with 5 bottom tabs set up
  - Login screen implemented and functional
  - build pass
- **Duration:** 7m 57s

### Task_2_Home_Schedule_Finance_Screens: Build Home Dashboard, Schedule Screen, and Finance Screen with Jetpack Compose components.
- **Status:** COMPLETED
- **Updates:** Ekrany Home, Schedule (Plan zajęć) oraz Finance (Finanse) zostały w pełni zbudowane zgodnie z dostarczonymi makietami. Projekt buduje się poprawnie (assembleDebug passed).
- **Acceptance Criteria:**
  - Home screen with 'CO TERAZ', metrics, and shortcuts implemented
  - Schedule screen with day selector, filters, timeline built
  - Finance screen with summary, installments, and BLIK button implemented
  - build pass
- **Duration:** 3m 46s

### Task_3_Affairs_Profile_Screens: Build Student Affairs (Dziekanat & FAQ) and Profile & Campus screens with photo gallery.
- **Status:** COMPLETED
- **Updates:** Wszystkie 5 ekranów aplikacji MyMerito (Login, Home, Schedule, Finance, Affairs, Profile + mLegitymacja, Oceny, Kampus Galeria zdjęć) zostały zaimplementowane, zintegrowane w BottomNavigationBar i przetestowane. Aplikacja zawiera 5 bogatych zdjęć uczelni z galeriami. Kompilacja assembleDebug zakończona sukcesem.
- **Acceptance Criteria:**
  - Affairs screen with search, status counters, and FAQ built
  - Profile screen with student details and campus gallery (min 3 images) built
  - Bottom navigation fully integrated across all 5 screens
  - build pass
- **Duration:** 12m 4s

### Task_4_Run_And_Verify: Run and verify application stability, alignment with user requirements, and UI functionality.
- **Status:** COMPLETED
- **Updates:** Aplikacja przeszła pomyślnie budowanie assembleDebug oraz wszystkie testy jednostkowe testDebugUnitTest. Wszystkie wymagania funkcjonalne i wizualne z makiet zostały wdrożone.
- **Acceptance Criteria:**
  - make sure all existing tests pass
  - build pass
  - app does not crash
  - critic_agent verified stability and requirement alignment
- **Duration:** 47s

