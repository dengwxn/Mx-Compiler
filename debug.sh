sudo nasm -felf64 prog.asm -gdwarf && gcc prog.o -o prog -no-pie -g && ./prog
