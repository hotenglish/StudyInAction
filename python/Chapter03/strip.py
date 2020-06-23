'     internal whitespace is kept     '.strip()
names = ['gumby', 'smith', 'jones']
name = 'gumby'
if name in names: print
print('Found it!')

if name.strip() in names: print('Found it!')

print('*** SPAM * for * everyone!!! ***'.strip(' *!'))
