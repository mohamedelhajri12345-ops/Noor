from pathlib import Path
p=Path('islamic-base/lib/main.dart')
s=p.read_text()
if "import 'audio_service.dart';" not in s:
    s="import 'audio_service.dart';\n"+s
s=s.replace("ListTile(leading:const Icon(Icons.play_circle),title:const Text('تشغيل التلاوة'),onTap:()=>Navigator.pop(c))", "ListTile(leading:const Icon(Icons.play_circle),title:const Text('تشغيل التلاوة'),onTap:(){NoorAudio.playQuran();Navigator.pop(c);})")
s=s.replace("ListTile(leading:const Icon(Icons.notifications_active_outlined),label:'إعداد الأذان')", "ListTile(leading:const Icon(Icons.notifications_active_outlined),title:const Text('اختبار إشعار الأذان'),onTap:(){NoorAudio.notifyPrayer('الظهر');})")
s=s.replace("ListTile(leading:const Icon(Icons.dark_mode,color:crimson),title:const Text('تبديل المظهر'),onTap:onTheme)", "ListTile(leading:const Icon(Icons.volume_up,color:crimson),title:const Text('إيقاف التلاوة'),onTap:(){NoorAudio.stop();}),ListTile(leading:const Icon(Icons.dark_mode,color:crimson),title:const Text('تبديل المظهر'),onTap:onTheme)")
p.write_text(s)
