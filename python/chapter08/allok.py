while True:
    try:
        x = int(input('Enter the first number:'))
        y = int(input('Enter the second number:'))
        print(x / y)
    except Exception as e:
        print("Invalid input:", e)
        print('Please try again')
    else:
        break
