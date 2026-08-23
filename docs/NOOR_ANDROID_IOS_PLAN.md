# Noor Android + iOS Implementation Plan

## Android
- Automatic location for prayer calculations.
- Notification and exact-alarm capability where permitted.
- Background audio service for Quran and Adhan.
- Boot/time-change rescheduling for prayer reminders.
- Start.io Android integration isolated from audio playback.

## iOS
- Core Location for prayer location.
- Local notification scheduling.
- AVAudioSession/background audio configuration for Quran playback.
- App lifecycle-safe audio handling.
- iOS ad integration isolated from worship/audio flows.

## Shared Flutter layer
- Prayer calculation service.
- Quran data/repository.
- Audio controller abstraction.
- Notification abstraction.
- Qibla calculation.
- Tasbeeh state persistence.
- Settings persistence.
- Localization and theme system.
