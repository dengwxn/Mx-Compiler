	global  main
	extern 	printf
	extern 	malloc
	extern 	strcpy
	extern 	scanf
	extern 	strlen
	extern 	sscanf
	extern 	sprintf
	extern 	memcpy
	extern 	strcmp
	extern 	puts

section .text
@global_var_decl:
	sub  	rsp, 8
@global_var_decl.entry.1:
@global_var_decl.exit.2:
	add  	rsp, 8
	ret
qpow:
	sub  	rsp, 152
	mov  	qword [rsp + 120], rdi
	mov  	qword [rsp + 96], rsi
	mov  	qword [rsp + 56], rdx
qpow.entry.1:
	mov  	qword [rsp + 144], 1
	mov  	r11, qword [rsp + 144]
	mov  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 120]
	mov  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 128]
	mov  	qword [rsp + 112], r11
qpow.whileHeader.2:
	mov  	r11, qword [rsp + 96]
	mov  	qword [rsp + 104], r11
	cmp  	qword [rsp + 104], 0
	jg  	qpow.whileBlock.3
	jmp  	qpow.whileExit.7
qpow.whileBlock.3:
	mov  	r11, qword [rsp + 96]
	mov  	qword [rsp + 88], r11
	and  	qword [rsp + 88], 1
	mov  	r11, qword [rsp + 88]
	mov  	qword [rsp + 80], r11
	cmp  	qword [rsp + 80], 1
	je  	qpow.ifTrue.4
	jmp  	qpow.ifFalse.5
qpow.ifTrue.4:
	mov  	r11, qword [rsp + 136]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 72]
	imul  	r11, qword [rsp + 112]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 72]
	mov  	qword [rsp + 64], r11
	mov  	rax, qword [rsp + 64]
	cqo
	idiv  	qword [rsp + 56]
	mov  	qword [rsp + 64], rdx
	mov  	r11, qword [rsp + 64]
	mov  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 48]
	mov  	qword [rsp + 136], r11
	jmp  	qpow.ifExit.6
qpow.ifFalse.5:
qpow.ifExit.6:
	mov  	r11, qword [rsp + 112]
	mov  	qword [rsp + 40], r11
	mov  	r11, qword [rsp + 40]
	imul  	r11, qword [rsp + 112]
	mov  	qword [rsp + 40], r11
	mov  	r11, qword [rsp + 40]
	mov  	qword [rsp + 32], r11
	mov  	rax, qword [rsp + 32]
	cqo
	idiv  	qword [rsp + 56]
	mov  	qword [rsp + 32], rdx
	mov  	r11, qword [rsp + 32]
	mov  	qword [rsp + 24], r11
	mov  	r11, qword [rsp + 24]
	mov  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 96]
	mov  	qword [rsp + 16], r11
	mov  	rax, qword [rsp + 16]
	cqo
	mov  	r11, 2
	idiv  	r11
	mov  	qword [rsp + 16], rax
	mov  	r11, qword [rsp + 16]
	mov  	qword [rsp + 8], r11
	mov  	r11, qword [rsp + 8]
	mov  	qword [rsp + 96], r11
	jmp  	qpow.whileHeader.2
qpow.whileExit.7:
	mov  	rax, qword [rsp + 136]
qpow.exit.8:
	add  	rsp, 152
	ret
main:
	sub  	rsp, 24
main.entry.1:
	call  	@global_var_decl
	mov  	rdi, 2
	mov  	rsi, 10
	mov  	rdx, 10000
	call  	qpow
	mov  	qword [rsp + 16], rax
	mov  	rdi, qword [rsp + 16]
	call  	toString
	mov  	qword [rsp + 8], rax
	mov  	rdi, qword [rsp + 8]
	call  	println
	mov  	rax, 0
main.exit.2:
	add  	rsp, 24
	ret

section .data

section .bss

section	.text
_.size:
	mov  	rax, qword [rdi - 8]
	ret
print:
	mov  	rsi, rdi
	mov  	rdi, _print_format
	sub  	rsp, 8
	call  	printf
	add  	rsp, 8
	ret
println:
	sub  	rsp, 8
	call  	puts
	add  	rsp, 8
	ret
getString:
	push  	r15
	mov  	rdi, 300
	call  	malloc
	mov  	r15, rax
	add  	r15, 8
	mov  	rdi, _getString_format
	mov  	rsi, r15
	call  	scanf
	mov  	rdi, r15
	call  	strlen
	mov  	qword [r15 - 8], rax
	mov  	rax, r15
	pop  	r15
	ret
getInt:
	mov  	rdi, _getInt_format
	mov  	rsi, @getInt_buffer
	sub  	rsp, 8
	call  	scanf
	add  	rsp, 8
	mov  	rax, qword [@getInt_buffer]
	ret
toString:
	push  	r15
	push  	rdi
	mov  	rdi, 20
	sub  	rsp, 8
	call  	malloc
	add  	rsp, 8
	mov  	r15, rax
	add  	r15, 8
	mov  	rdi, r15
	mov  	rsi, _toString_format
	pop  	rdx
	call  	sprintf
	mov  	rdi, r15
	call  	strlen
	mov  	qword [r15 - 8], rax
	mov  	rax, r15
	pop  	r15
	ret
string.length:
	mov  	rax, qword [rdi - 8]
	ret
string.substring:
	push  	r15
	mov  	r15, rdi
	add  	r15, rsi
	push  	r14
	mov  	r14, rdx
	sub  	r14, rsi
	add  	r14, 1
	mov  	rdi, r14
	add  	rdi, 9
	sub  	rsp, 8
	call  	malloc
	add  	rsp, 8
	add  	rax, 8
	mov  	rdi, rax
	mov  	rsi, r15
	mov  	rdx, r14
	sub  	rsp, 8
	call  	memcpy
	add  	rsp, 8
	mov  	qword [rax - 8], r14
	mov  	r15, rax
	add  	r15, r14
	mov  	r15, 0
	pop  	r14
	pop  	r15
	ret
string.parseInt:
	mov  	rsi, _parseInt_format
	mov  	rdx, @parseInt_buffer
	sub  	rsp, 8
	call  	sscanf
	add  	rsp, 8
	mov  	rax, qword [@parseInt_buffer]
	ret
string.ord:
	add  	rdi, rsi
	movsx  	rax, byte [rdi]
	ret
string.add:
	push  	r15
	push  	r14
	push  	r13
	mov  	r15, qword [rdi - 8]
	add  	r15, qword [rsi - 8]
	add  	r15, 9
	mov  	r14, rdi
	mov  	r13, rsi
	mov  	rdi, r15
	call  	malloc
	sub  	r15, 9
	mov  	qword [rax], r15
	mov  	r15, rax
	add  	r15, 8
	mov  	rdi, r15
	mov  	rsi, r14
	call  	strcpy
	add  	r15, qword [r14 - 8]
	mov  	r14, rax
	mov  	rdi, r15
	mov  	rsi, r13
	call  	strcpy
	mov  	rax, r14
	pop  	r13
	pop  	r14
	pop  	r15
	ret
string.e:
	sub  	rsp, 8
	call  	strcmp
	add  	rsp, 8
	cmp  	eax, 0
	mov  	rax, 0
	sete  	al
	ret
string.ne:
	sub  	rsp, 8
	call  	strcmp
	add  	rsp, 8
	cmp  	eax, 0
	mov  	rax, 0
	setne  	al
	ret
string.g:
	sub  	rsp, 8
	call  	strcmp
	add  	rsp, 8
	cmp  	eax, 0
	mov  	rax, 0
	setg  	al
	ret
string.ge:
	sub  	rsp, 8
	call  	strcmp
	add  	rsp, 8
	cmp  	eax, 0
	mov  	rax, 0
	setge  	al
	ret
string.l:
	sub  	rsp, 8
	call  	strcmp
	add  	rsp, 8
	cmp  	eax, 0
	mov  	rax, 0
	setl  	al
	ret
string.le:
	sub  	rsp, 8
	call  	strcmp
	add  	rsp, 8
	cmp  	eax, 0
	mov  	rax, 0
	setle  	al
	ret

section	.data
_print_format:
	db  	"%s", 0
_getString_format:
	db  	"%s", 0
_getInt_format:
	db  	"%ld", 0
_toString_format:
	db  	"%ld", 0
_parseInt_format:
	db  	"%ld", 0

section	.bss
@getInt_buffer:
	resq  	1
@parseInt_buffer:
	resq  	1

