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
main:
	sub  	rsp, 360
main.entry.1:
	call  	@global_var_decl
main.new.body.2:
	mov  	qword [rsp + 352], 100
	add  	qword [rsp + 352], 1
	sal  	qword [rsp + 352], 3
	mov  	rdi, qword [rsp + 352]
	call  	malloc
	mov  	qword [rsp + 344], rax
	mov  	r11, qword [rsp + 344]
	mov  	qword [ler8 + 0], 100
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 336], r11
	add  	qword [rsp + 336], 8
	mov  	r11, qword [rsp + 336]
	mov  	qword [rsp + 328], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 320], r11
	mov  	r11, qword [rsp + 352]
	add  	qword [rsp + 320], r11
main.new.cond.3:
	mov  	r11, qword [rsp + 336]
	cmp  	r11, qword [rsp + 320]
	jl  	main.new.body.4
	jmp  	main.new.exit.9
main.new.body.4:
	mov  	qword [rsp + 312], 200
	add  	qword [rsp + 312], 1
	sal  	qword [rsp + 312], 3
	mov  	rdi, qword [rsp + 312]
	call  	malloc
	mov  	qword [rsp + 304], rax
	mov  	r11, qword [rsp + 304]
	mov  	qword [ler8 + 0], 200
	mov  	r11, qword [rsp + 304]
	mov  	qword [rsp + 296], r11
	add  	qword [rsp + 296], 8
	mov  	r11, qword [rsp + 336]
	mov  	r11, qword [rsp + 296]
	mov  	qword [ler8 + 0], r11
	mov  	r11, qword [rsp + 304]
	mov  	qword [rsp + 288], r11
	mov  	r11, qword [rsp + 312]
	add  	qword [rsp + 288], r11
main.new.cond.5:
	mov  	r11, qword [rsp + 296]
	cmp  	r11, qword [rsp + 288]
	jl  	main.new.body.6
	add  	qword [rsp + 336], 8
	jmp  	main.new.cond.3
main.new.body.6:
	mov  	qword [rsp + 280], 300
	add  	qword [rsp + 280], 1
	sal  	qword [rsp + 280], 3
	mov  	rdi, qword [rsp + 280]
	call  	malloc
	mov  	qword [rsp + 272], rax
	mov  	r11, qword [rsp + 272]
	mov  	qword [ler8 + 0], 300
	mov  	r11, qword [rsp + 272]
	mov  	qword [rsp + 264], r11
	add  	qword [rsp + 264], 8
	mov  	r11, qword [rsp + 296]
	mov  	r11, qword [rsp + 264]
	mov  	qword [ler8 + 0], r11
	mov  	r11, qword [rsp + 272]
	mov  	qword [rsp + 256], r11
	mov  	r11, qword [rsp + 280]
	add  	qword [rsp + 256], r11
main.new.cond.7:
	mov  	r11, qword [rsp + 264]
	cmp  	r11, qword [rsp + 256]
	jl  	main.new.body.8
	add  	qword [rsp + 296], 8
	jmp  	main.new.cond.5
main.new.body.8:
	mov  	qword [rsp + 248], 500
	add  	qword [rsp + 248], 1
	sal  	qword [rsp + 248], 3
	mov  	rdi, qword [rsp + 248]
	call  	malloc
	mov  	qword [rsp + 240], rax
	mov  	r11, qword [rsp + 240]
	mov  	qword [ler8 + 0], 500
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 232], r11
	add  	qword [rsp + 232], 8
	mov  	r11, qword [rsp + 264]
	mov  	r11, qword [rsp + 232]
	mov  	qword [ler8 + 0], r11
	add  	qword [rsp + 264], 8
	jmp  	main.new.cond.7
main.new.exit.9:
	mov  	r11, qword [rsp + 328]
	mov  	qword [rsp + 224], r11
	mov  	r11, qword [rsp + 224]
	mov  	qword [rsp + 216], r11
	mov  	qword [rsp + 208], 3
	sal  	qword [rsp + 208], 3
	mov  	r11, qword [rsp + 208]
	add  	qword [rsp + 216], r11
	mov  	r11, qword [rsp + 216]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 200], r11
	mov  	r11, qword [rsp + 200]
	mov  	qword [rsp + 192], r11
	mov  	qword [rsp + 184], 7
	sal  	qword [rsp + 184], 3
	mov  	r11, qword [rsp + 184]
	add  	qword [rsp + 192], r11
	mov  	r11, qword [rsp + 192]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 176], r11
	mov  	r11, qword [rsp + 200]
	mov  	qword [rsp + 168], r11
	mov  	qword [rsp + 160], 7
	sal  	qword [rsp + 160], 3
	mov  	r11, qword [rsp + 160]
	add  	qword [rsp + 168], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 168], r11
	mov  	qword [rsp + 160], 8
	sal  	qword [rsp + 160], 3
	mov  	r11, qword [rsp + 160]
	add  	qword [rsp + 168], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 168], r11
	mov  	qword [rsp + 160], 9
	sal  	qword [rsp + 160], 3
	mov  	r11, qword [rsp + 160]
	add  	qword [rsp + 168], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 152], r11
	mov  	r11, qword [rsp + 176]
	mov  	qword [rsp + 144], r11
	mov  	qword [rsp + 136], 2
	sal  	qword [rsp + 136], 3
	mov  	r11, qword [rsp + 136]
	add  	qword [rsp + 144], r11
	mov  	r11, qword [rsp + 144]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 144], r11
	mov  	qword [rsp + 136], 1
	sal  	qword [rsp + 136], 3
	mov  	r11, qword [rsp + 136]
	add  	qword [rsp + 144], r11
	mov  	r11, qword [rsp + 200]
	mov  	qword [rsp + 128], r11
	mov  	qword [rsp + 120], 1
	sal  	qword [rsp + 120], 3
	mov  	r11, qword [rsp + 120]
	add  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 128]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 128], r11
	mov  	qword [rsp + 120], 2
	sal  	qword [rsp + 120], 3
	mov  	r11, qword [rsp + 120]
	add  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 128]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 128], r11
	mov  	qword [rsp + 120], 3
	sal  	qword [rsp + 120], 3
	mov  	r11, qword [rsp + 120]
	add  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 144]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 128]
	mov  	r11, qword [rsp + 112]
	cmp  	r11, qword [ler8 + 0]
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 112], r11
	cmp  	qword [rsp + 112], 1
	je  	main.lhsTrue.10
	jmp  	main.lhsFalse.11
main.lhsTrue.10:
	mov  	r11, qword [rsp + 176]
	mov  	qword [rsp + 104], r11
	mov  	qword [rsp + 96], 2
	sal  	qword [rsp + 96], 3
	mov  	r11, qword [rsp + 96]
	add  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 104]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 104], r11
	mov  	qword [rsp + 96], 2
	sal  	qword [rsp + 96], 3
	mov  	r11, qword [rsp + 96]
	add  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 224]
	mov  	qword [rsp + 88], r11
	mov  	qword [rsp + 80], 1
	sal  	qword [rsp + 80], 3
	mov  	r11, qword [rsp + 80]
	add  	qword [rsp + 88], r11
	mov  	r11, qword [rsp + 88]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 88], r11
	mov  	qword [rsp + 80], 2
	sal  	qword [rsp + 80], 3
	mov  	r11, qword [rsp + 80]
	add  	qword [rsp + 88], r11
	mov  	r11, qword [rsp + 88]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 88], r11
	mov  	qword [rsp + 80], 3
	sal  	qword [rsp + 80], 3
	mov  	r11, qword [rsp + 80]
	add  	qword [rsp + 88], r11
	mov  	r11, qword [rsp + 88]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 88], r11
	mov  	qword [rsp + 80], 4
	sal  	qword [rsp + 80], 3
	mov  	r11, qword [rsp + 80]
	add  	qword [rsp + 88], r11
	mov  	r11, qword [rsp + 104]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 88]
	mov  	r11, qword [rsp + 72]
	cmp  	r11, qword [ler8 + 0]
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 72], r11
	cmp  	qword [rsp + 72], 1
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 64], r11
	jmp  	main.logicExit.12
main.lhsFalse.11:
	mov  	qword [rsp + 64], 0
main.logicExit.12:
	cmp  	qword [rsp + 64], 1
	je  	main.lhsTrue.13
	jmp  	main.lhsFalse.14
main.lhsTrue.13:
	mov  	r11, qword [rsp + 176]
	mov  	qword [rsp + 56], r11
	mov  	qword [rsp + 48], 2
	sal  	qword [rsp + 48], 3
	mov  	r11, qword [rsp + 48]
	add  	qword [rsp + 56], r11
	mov  	r11, qword [rsp + 56]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 56], r11
	mov  	qword [rsp + 48], 3
	sal  	qword [rsp + 48], 3
	mov  	r11, qword [rsp + 48]
	add  	qword [rsp + 56], r11
	mov  	r11, qword [rsp + 56]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 40], r11
	mov  	r11, qword [rsp + 40]
	cmp  	r11, qword [rsp + 32]
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 40], r11
	cmp  	qword [rsp + 40], 1
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 24], r11
	jmp  	main.logicExit.15
main.lhsFalse.14:
	mov  	qword [rsp + 24], 0
main.logicExit.15:
	cmp  	qword [rsp + 24], 1
	je  	main.ifTrue.16
	jmp  	main.ifFalse.17
main.ifTrue.16:
	mov  	r11, qword [rsp + 200]
	mov  	qword [rsp + 16], r11
	mov  	qword [rsp + 8], 1
	sal  	qword [rsp + 8], 3
	mov  	r11, qword [rsp + 8]
	add  	qword [rsp + 16], r11
	mov  	r11, qword [rsp + 16]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 16], r11
	mov  	qword [rsp + 8], 2
	sal  	qword [rsp + 8], 3
	mov  	r11, qword [rsp + 8]
	add  	qword [rsp + 16], r11
	mov  	r11, qword [rsp + 16]
	mov  	r11, qword [ler8 + 0]
	mov  	qword [rsp + 16], r11
	mov  	qword [rsp + 8], 3
	sal  	qword [rsp + 8], 3
	mov  	r11, qword [rsp + 8]
	add  	qword [rsp + 16], r11
	mov  	r11, qword [rsp + 16]
	inc  	qword [ler8 + 0]
	jmp  	main.ifExit.18
main.ifFalse.17:
main.ifExit.18:
main.exit.19:
	add  	rsp, 360
	ret
@global_var_decl:
	sub  	rsp, 8
@global_var_decl.entry.1:
@global_var_decl.exit.2:
	add  	rsp, 8
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

