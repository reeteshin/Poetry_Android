import os

java_dir = 'app/src/main/java/com/KIPTSOFT/Shayari'
java_files = ['Birthday.java', 'Dard.java', 'DardEdilS.java', 'Desh.java', 'Dosti.java', 'FDewali.java', 'FHoli.java', 'Festival.java', 'FestivalIsolated.java', 'Funny.java', 'Funnystatus.java', 'GirlAttitude.java', 'Life.java', 'LifeS.java', 'Love.java', 'MahakalS.java', 'Miss.java', 'Morning.java', 'Motivation.java', 'Night.java', 'Poems.java', 'RandomS.java', 'Romantic.java', 'Sad.java', 'Sharabi.java', 'Sorry.java', 'Status.java', 'Truee.java', 'Valentine.java', 'Yaad.java', 'attitude.java', 'attitudestatus.java', 'beauty.java', 'bewafa.java', 'twolinestatus.java']

layout_dir = 'app/src/main/res/layout'
layout_files = ['activity_attitude.xml', 'activity_attitudestatus.xml', 'activity_beauty.xml', 'activity_bewafa.xml', 'activity_birthday.xml', 'activity_dard.xml', 'activity_dard_edil_s.xml', 'activity_desh.xml', 'activity_dosti.xml', 'activity_f_dewali.xml', 'activity_f_holi.xml', 'activity_festival.xml', 'activity_festival_isolated.xml', 'activity_funny.xml', 'activity_funnystatus.xml', 'activity_girl_attitude.xml', 'activity_life.xml', 'activity_life_s.xml', 'activity_love.xml', 'activity_mahakal_s.xml', 'activity_miss.xml', 'activity_morning.xml', 'activity_motivation.xml', 'activity_night.xml', 'activity_poems.xml', 'activity_random_s.xml', 'activity_romantic.xml', 'activity_sad.xml', 'activity_sharabi.xml', 'activity_sorry.xml', 'activity_status.xml', 'activity_truee.xml', 'activity_twolinestatus.xml', 'activity_valentine.xml', 'activity_yaad.xml']

for f in java_files:
    path = os.path.join(java_dir, f)
    if os.path.exists(path):
        os.remove(path)

for f in layout_files:
    path = os.path.join(layout_dir, f)
    if os.path.exists(path):
        os.remove(path)

print("Files deleted.")
