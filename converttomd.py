from pathlib import Path
# p = Path('C:\\Users\\User\\Desktop\\Poems\\aaron-southwick\\Butte.txt')



# p.rename(p.with_suffix('.md'))




import os
rootdir = 'C:\\Users\\User\\Desktop\\Poems\\poems'


for subdir, dirs, files in os.walk(rootdir):
    for file in files:
        p = Path(os.path.join(subdir, file))
        p.rename(p.with_suffix('.md'))