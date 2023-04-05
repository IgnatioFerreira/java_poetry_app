from pathlib import Path
# p = Path('C:\\Users\\User\\Desktop\\Poems\\aaron-southwick\\Butte.txt')



# p.rename(p.with_suffix('.md'))




import os
# rootdir = 'C:\\Users\\User\\Desktop\\Poems\\poems'
# for subdir, dirs, files in os.walk(rootdir):
#     for file in files:
#         p = Path(os.path.join(subdir, file))
#         p.rename(p.with_suffix('.md'))



# i = 0
# rootdir = 'C:\\Users\\User\\java_poetry_app\\poems'
# for subdir, dirs, files in os.walk(rootdir):
#     for file in files:
#         i += 1
# print(i)

# import random
# ranint = random.randint(0,30246)
# print(ranint)
# i = 0
# rootdir = 'C:\\Users\\User\\java_poetry_app\\poems'
# for subdir, dirs, files in os.walk(rootdir):
#     for file in files:
#         i += 1
#         if i == ranint:
#             print(os.path.join(subdir, file))
#             with open(os.path.join(subdir, file), 'r') as f:
#                 print(f.read())


import random
ranint = random.randint(0,30246)
i = 0
rootdir = 'C:\\Poetry\\poems'
print(ranint)
for subdir, dirs, files in os.walk(rootdir):
    for file in files:
        i += 1
        if i == ranint:
            with open(os.path.join(subdir, file), 'r') as f:
                print(f.read())