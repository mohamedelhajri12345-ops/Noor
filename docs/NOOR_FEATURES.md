# Noor — Complete Feature Architecture

## Product
Noor is a premium Islamic companion for Android and iOS.

## Visual identity
- Olive: #4E5635
- Deep olive: #252B1B
- Crimson: #8D2436
- Silver: #B9BEC6
- Ivory: #F6F2E8

## Core modules
1. Home dashboard
2. Quran reader and search
3. Continuous Quran audio and background playback
4. Prayer times with automatic location
5. Prayer countdown and next prayer
6. Adhan scheduling and multiple Adhan sounds
7. Prayer notifications
8. Qibla compass
9. Azkar and Duas
10. Digital Tasbeeh
11. Islamic library
12. Daily Hizb / daily worship reminders
13. Worship notebook and progress tracking
14. Islamic AI assistant with source-aware answers
15. Offline-first local data where possible
16. Start.io Banner and Native ads with context-aware suppression during Quran/audio/Adhan
17. Arabic RTL and English localization
18. Light and dark premium themes
19. Animated spiritual splash and subtle motion backgrounds

## Navigation
Home | Quran | Prayer | Azkar | More

## Quality requirements
- Features must be connected to real UI and usable flows.
- Android and iOS platform differences must be isolated behind services.
- Quran and Adhan audio must not be interrupted by advertising UI.
- Location and notification permissions must be requested contextually.
- Settings must persist locally.
- Release builds must be validated by CI.
