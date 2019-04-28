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
exchange:
	sub  	rsp, 120
exchange.entry.1:
	mov  	r11, qword [rsp + 104]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 88]
	mov  	qword [rsp + 96], r11
	sal  	qword [rsp + 96], 3
	mov  	r11, qword [rsp + 96]
	add  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 112]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 80], r11
	mov  	r11, qword [rsp + 80]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 104]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 64], r11
	mov  	r11, qword [rsp + 88]
	mov  	qword [rsp + 56], r11
	sal  	qword [rsp + 56], 3
	mov  	r11, qword [rsp + 56]
	add  	qword [rsp + 64], r11
	mov  	r11, qword [rsp + 104]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 32]
	mov  	qword [rsp + 40], r11
	sal  	qword [rsp + 40], 3
	mov  	r11, qword [rsp + 40]
	add  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 48]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 24], r11
	mov  	r11, qword [rsp + 64]
	mov  	r11, qword [rsp + 24]
	mov  	qword [r11 + 0], r11
	mov  	r11, qword [rsp + 104]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 16], r11
	mov  	r11, qword [rsp + 32]
	mov  	qword [rsp + 8], r11
	sal  	qword [rsp + 8], 3
	mov  	r11, qword [rsp + 8]
	add  	qword [rsp + 16], r11
	mov  	r11, qword [rsp + 72]
	mov  	qword [rsp + 0], r11
	mov  	r11, qword [rsp + 16]
	mov  	r11, qword [rsp + 0]
	mov  	qword [r11 + 0], r11
exchange.exit.2:
	add  	rsp, 120
	ret
makeHeap:
	sub  	rsp, 280
makeHeap.entry.1:
	mov  	r11, qword [rsp + 264]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 272], r11
	sub  	qword [rsp + 272], 1
	mov  	r11, qword [rsp + 272]
	mov  	qword [rsp + 256], r11
	mov  	rax, qword [rsp + 256]
	cqo
	idiv  	2
	mov  	qword [rsp + 256], rax
	mov  	r11, qword [rsp + 256]
	mov  	qword [rsp + 248], r11
	mov  	r11, qword [rsp + 248]
	mov  	qword [rsp + 240], r11
	mov  	qword [rsp + 232], 0
	mov  	r11, qword [rsp + 232]
	mov  	qword [rsp + 224], r11
	mov  	qword [rsp + 216], 0
	mov  	r11, qword [rsp + 216]
	mov  	qword [rsp + 208], r11
makeHeap.whileHeader.2:
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 200], r11
	cmp  	qword [rsp + 200], 0
	mov  	r11, 0
	setge  	r11b
	mov  	qword [rsp + 200], r11
	cmp  	qword [rsp + 200], 1
	je  	makeHeap.whileBlock.3
	jmp  	makeHeap.whileExit.13
makeHeap.whileBlock.3:
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 192], r11
	mov  	r11, qword [rsp + 192]
	imul  	r11, 2
	mov  	qword [rsp + 192], r11
	mov  	r11, qword [rsp + 192]
	mov  	qword [rsp + 184], r11
	mov  	r11, qword [rsp + 184]
	mov  	qword [rsp + 208], r11
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 176], r11
	mov  	r11, qword [rsp + 176]
	imul  	r11, 2
	mov  	qword [rsp + 176], r11
	mov  	r11, qword [rsp + 176]
	mov  	qword [rsp + 168], r11
	add  	qword [rsp + 168], 1
	mov  	r11, qword [rsp + 168]
	mov  	qword [rsp + 160], r11
	mov  	r11, qword [rsp + 264]
	mov  	r11, qword [rsp + 160]
	cmp  	r11, qword [r11 + 0]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 160], r11
	cmp  	qword [rsp + 160], 1
	je  	makeHeap.lhsTrue.4
	jmp  	makeHeap.lhsFalse.5
makeHeap.lhsTrue.4:
	mov  	r11, qword [rsp + 144]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 152], r11
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 136]
	imul  	r11, 2
	mov  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 136]
	mov  	qword [rsp + 128], r11
	add  	qword [rsp + 128], 1
	mov  	r11, qword [rsp + 128]
	mov  	qword [rsp + 120], r11
	sal  	qword [rsp + 120], 3
	mov  	r11, qword [rsp + 120]
	add  	qword [rsp + 152], r11
	mov  	r11, qword [rsp + 144]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 104]
	imul  	r11, 2
	mov  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 104]
	mov  	qword [rsp + 96], r11
	sal  	qword [rsp + 96], 3
	mov  	r11, qword [rsp + 96]
	add  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 152]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 88], r11
	mov  	r11, qword [rsp + 112]
	mov  	r11, qword [rsp + 88]
	cmp  	r11, qword [r11 + 0]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 88], r11
	cmp  	qword [rsp + 88], 1
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 80], r11
	jmp  	makeHeap.logicExit.6
makeHeap.lhsFalse.5:
	mov  	qword [rsp + 80], 0
makeHeap.logicExit.6:
	cmp  	qword [rsp + 80], 1
	je  	makeHeap.ifTrue.7
	jmp  	makeHeap.ifFalse.8
makeHeap.ifTrue.7:
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 72]
	imul  	r11, 2
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 72]
	mov  	qword [rsp + 64], r11
	add  	qword [rsp + 64], 1
	mov  	r11, qword [rsp + 64]
	mov  	qword [rsp + 56], r11
	mov  	r11, qword [rsp + 56]
	mov  	qword [rsp + 208], r11
	jmp  	makeHeap.ifExit.9
makeHeap.ifFalse.8:
makeHeap.ifExit.9:
	mov  	r11, qword [rsp + 144]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 40], r11
	sal  	qword [rsp + 40], 3
	mov  	r11, qword [rsp + 40]
	add  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 144]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 32], r11
	mov  	r11, qword [rsp + 208]
	mov  	qword [rsp + 24], r11
	sal  	qword [rsp + 24], 3
	mov  	r11, qword [rsp + 24]
	add  	qword [rsp + 32], r11
	mov  	r11, qword [rsp + 48]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 16], r11
	mov  	r11, qword [rsp + 32]
	mov  	r11, qword [rsp + 16]
	cmp  	r11, qword [r11 + 0]
	mov  	r11, 0
	setg  	r11b
	mov  	qword [rsp + 16], r11
	cmp  	qword [rsp + 16], 1
	je  	makeHeap.ifTrue.10
	jmp  	makeHeap.ifFalse.11
makeHeap.ifTrue.10:
	mov  	rdi, qword [rsp + 240]
	mov  	rsi, qword [rsp + 208]
	call  	exchange
	jmp  	makeHeap.ifExit.12
makeHeap.ifFalse.11:
makeHeap.ifExit.12:
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 8], r11
	sub  	qword [rsp + 8], 1
	mov  	r11, qword [rsp + 8]
	mov  	qword [rsp + 0], r11
	mov  	r11, qword [rsp + 0]
	mov  	qword [rsp + 240], r11
	jmp  	makeHeap.whileHeader.2
makeHeap.whileExit.13:
	mov  	rax, 0
makeHeap.exit.14:
	add  	rsp, 280
	ret
heapSort:
	sub  	rsp, 216
heapSort.entry.1:
	mov  	qword [rsp + 208], 0
	mov  	r11, qword [rsp + 208]
	mov  	qword [rsp + 200], r11
	mov  	qword [rsp + 192], 0
	mov  	r11, qword [rsp + 192]
	mov  	qword [rsp + 184], r11
heapSort.forHeader.2:
	mov  	r11, qword [rsp + 184]
	mov  	qword [rsp + 176], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [rsp + 176]
	cmp  	r11, qword [r11 + 0]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 176], r11
	cmp  	qword [rsp + 176], 1
	je  	heapSort.forBlock.3
	jmp  	heapSort.forExit.5
heapSort.forBlock.3:
	mov  	r11, qword [rsp + 152]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 160], r11
	mov  	qword [rsp + 144], 0
	sal  	qword [rsp + 144], 3
	mov  	r11, qword [rsp + 144]
	add  	qword [rsp + 160], r11
	mov  	r11, qword [rsp + 160]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 136]
	mov  	qword [rsp + 200], r11
	mov  	r11, qword [rsp + 152]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 128], r11
	mov  	qword [rsp + 120], 0
	sal  	qword [rsp + 120], 3
	mov  	r11, qword [rsp + 120]
	add  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 152]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 184]
	sub  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 104]
	mov  	qword [rsp + 96], r11
	sub  	qword [rsp + 96], 1
	mov  	r11, qword [rsp + 96]
	mov  	qword [rsp + 88], r11
	sal  	qword [rsp + 88], 3
	mov  	r11, qword [rsp + 88]
	add  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 112]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 80], r11
	mov  	r11, qword [rsp + 128]
	mov  	r11, qword [rsp + 80]
	mov  	qword [r11 + 0], r11
	mov  	r11, qword [rsp + 152]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 64], r11
	mov  	r11, qword [rsp + 184]
	sub  	qword [rsp + 64], r11
	mov  	r11, qword [rsp + 64]
	mov  	qword [rsp + 56], r11
	sub  	qword [rsp + 56], 1
	mov  	r11, qword [rsp + 56]
	mov  	qword [rsp + 48], r11
	sal  	qword [rsp + 48], 3
	mov  	r11, qword [rsp + 48]
	add  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 200]
	mov  	qword [rsp + 40], r11
	mov  	r11, qword [rsp + 72]
	mov  	r11, qword [rsp + 40]
	mov  	qword [r11 + 0], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 32], r11
	mov  	r11, qword [rsp + 184]
	sub  	qword [rsp + 32], r11
	mov  	r11, qword [rsp + 32]
	mov  	qword [rsp + 24], r11
	sub  	qword [rsp + 24], 1
	mov  	rdi, qword [rsp + 24]
	call  	adjustHeap
	mov  	qword [rsp + 16], rax
heapSort.forIncr.4:
	mov  	r11, qword [rsp + 184]
	mov  	qword [rsp + 8], r11
	add  	qword [rsp + 8], 1
	mov  	r11, qword [rsp + 8]
	mov  	qword [rsp + 0], r11
	mov  	r11, qword [rsp + 0]
	mov  	qword [rsp + 184], r11
	jmp  	heapSort.forHeader.2
heapSort.forExit.5:
	mov  	rax, 0
heapSort.exit.6:
	add  	rsp, 216
	ret
@global_var_decl:
	sub  	rsp, 8
@global_var_decl.entry.1:
@global_var_decl.exit.2:
	add  	rsp, 8
	ret
main:
	sub  	rsp, 248
main.entry.1:
	call  	@global_var_decl
	call  	string.parseInt
	mov  	qword [rsp + 240], rax
	mov  	r11, qword [rsp + 240]
	mov  	qword [rsp + 232], r11
	mov  	r11, qword [rsp + 224]
	mov  	r11, qword [rsp + 232]
	mov  	qword [r11 + 0], r11
main.new.body.2:
	mov  	r11, qword [rsp + 224]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 216], r11
	mov  	r11, qword [rsp + 216]
	mov  	qword [rsp + 208], r11
	add  	qword [rsp + 208], 1
	sal  	qword [rsp + 208], 3
	mov  	rdi, qword [rsp + 208]
	call  	malloc
	mov  	qword [rsp + 200], rax
	mov  	r11, qword [rsp + 200]
	mov  	r11, qword [rsp + 216]
	mov  	qword [r11 + 0], r11
	mov  	r11, qword [rsp + 200]
	mov  	qword [rsp + 192], r11
	add  	qword [rsp + 192], 8
	mov  	r11, qword [rsp + 192]
	mov  	qword [rsp + 184], r11
main.new.exit.3:
	mov  	r11, qword [rsp + 184]
	mov  	qword [rsp + 176], r11
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [rsp + 176]
	mov  	qword [r11 + 0], r11
	mov  	qword [rsp + 160], 0
	mov  	r11, qword [rsp + 160]
	mov  	qword [rsp + 152], r11
main.forHeader.4:
	call  	_.size
	mov  	qword [rsp + 144], rax
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 136]
	cmp  	r11, qword [rsp + 144]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 136], r11
	cmp  	qword [rsp + 136], 1
	je  	main.forBlock.5
	jmp  	main.forExit.7
main.forBlock.5:
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 120], r11
	sal  	qword [rsp + 120], 3
	mov  	r11, qword [rsp + 120]
	add  	qword [rsp + 128], r11
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 112], r11
	mov  	r11, qword [rsp + 128]
	mov  	r11, qword [rsp + 112]
	mov  	qword [r11 + 0], r11
main.forIncr.6:
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 104], r11
	add  	qword [rsp + 104], 1
	mov  	r11, qword [rsp + 104]
	mov  	qword [rsp + 96], r11
	mov  	r11, qword [rsp + 96]
	mov  	qword [rsp + 152], r11
	jmp  	main.forHeader.4
main.forExit.7:
	call  	makeHeap
	mov  	qword [rsp + 88], rax
	call  	heapSort
	mov  	qword [rsp + 80], rax
	mov  	qword [rsp + 72], 0
	mov  	r11, qword [rsp + 72]
	mov  	qword [rsp + 152], r11
main.forHeader.8:
	call  	_.size
	mov  	qword [rsp + 64], rax
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 56], r11
	mov  	r11, qword [rsp + 56]
	cmp  	r11, qword [rsp + 64]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 56], r11
	cmp  	qword [rsp + 56], 1
	je  	main.forBlock.9
	jmp  	main.forExit.11
main.forBlock.9:
	mov  	r11, qword [rsp + 168]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 40], r11
	sal  	qword [rsp + 40], 3
	mov  	r11, qword [rsp + 40]
	add  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 48]
	mov  	rdi, qword [r11 + 0]
	call  	toString
	mov  	qword [rsp + 32], rax
	mov  	rdi, qword [rsp + 32]
	mov  	rsi, _string_constant_0
	call  	string.add
	mov  	qword [rsp + 24], rax
	mov  	rdi, qword [rsp + 24]
	call  	print
main.forIncr.10:
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 16], r11
	add  	qword [rsp + 16], 1
	mov  	r11, qword [rsp + 16]
	mov  	qword [rsp + 8], r11
	mov  	r11, qword [rsp + 8]
	mov  	qword [rsp + 152], r11
	jmp  	main.forHeader.8
main.forExit.11:
	mov  	rdi, _string_constant_1
	call  	print
	mov  	rax, 0
main.exit.12:
	add  	rsp, 248
	ret
adjustHeap:
	sub  	rsp, 360
adjustHeap.entry.1:
	mov  	qword [rsp + 352], 0
	mov  	r11, qword [rsp + 352]
	mov  	qword [rsp + 344], r11
	mov  	qword [rsp + 336], 0
	mov  	r11, qword [rsp + 336]
	mov  	qword [rsp + 328], r11
	mov  	qword [rsp + 320], 0
	mov  	r11, qword [rsp + 320]
	mov  	qword [rsp + 312], r11
adjustHeap.whileHeader.2:
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 304], r11
	mov  	r11, qword [rsp + 304]
	imul  	r11, 2
	mov  	qword [rsp + 304], r11
	mov  	r11, qword [rsp + 304]
	mov  	qword [rsp + 296], r11
	mov  	r11, qword [rsp + 296]
	cmp  	r11, qword [rsp + 288]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 296], r11
	cmp  	qword [rsp + 296], 1
	je  	adjustHeap.whileBlock.3
	jmp  	adjustHeap.whileExit.13
adjustHeap.whileBlock.3:
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 280], r11
	mov  	r11, qword [rsp + 280]
	imul  	r11, 2
	mov  	qword [rsp + 280], r11
	mov  	r11, qword [rsp + 280]
	mov  	qword [rsp + 272], r11
	mov  	r11, qword [rsp + 272]
	mov  	qword [rsp + 328], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 264], r11
	mov  	r11, qword [rsp + 264]
	imul  	r11, 2
	mov  	qword [rsp + 264], r11
	mov  	r11, qword [rsp + 264]
	mov  	qword [rsp + 256], r11
	add  	qword [rsp + 256], 1
	mov  	r11, qword [rsp + 256]
	mov  	qword [rsp + 248], r11
	mov  	r11, qword [rsp + 248]
	cmp  	r11, qword [rsp + 288]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 248], r11
	cmp  	qword [rsp + 248], 1
	je  	adjustHeap.lhsTrue.4
	jmp  	adjustHeap.lhsFalse.5
adjustHeap.lhsTrue.4:
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 240], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 224], r11
	mov  	r11, qword [rsp + 224]
	imul  	r11, 2
	mov  	qword [rsp + 224], r11
	mov  	r11, qword [rsp + 224]
	mov  	qword [rsp + 216], r11
	add  	qword [rsp + 216], 1
	mov  	r11, qword [rsp + 216]
	mov  	qword [rsp + 208], r11
	sal  	qword [rsp + 208], 3
	mov  	r11, qword [rsp + 208]
	add  	qword [rsp + 240], r11
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 200], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 192], r11
	mov  	r11, qword [rsp + 192]
	imul  	r11, 2
	mov  	qword [rsp + 192], r11
	mov  	r11, qword [rsp + 192]
	mov  	qword [rsp + 184], r11
	sal  	qword [rsp + 184], 3
	mov  	r11, qword [rsp + 184]
	add  	qword [rsp + 200], r11
	mov  	r11, qword [rsp + 240]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 176], r11
	mov  	r11, qword [rsp + 200]
	mov  	r11, qword [rsp + 176]
	cmp  	r11, qword [r11 + 0]
	mov  	r11, 0
	setl  	r11b
	mov  	qword [rsp + 176], r11
	cmp  	qword [rsp + 176], 1
	mov  	r11, 0
	sete  	r11b
	mov  	qword [rsp + 168], r11
	jmp  	adjustHeap.logicExit.6
adjustHeap.lhsFalse.5:
	mov  	qword [rsp + 168], 0
adjustHeap.logicExit.6:
	cmp  	qword [rsp + 168], 1
	je  	adjustHeap.ifTrue.7
	jmp  	adjustHeap.ifFalse.8
adjustHeap.ifTrue.7:
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 160], r11
	mov  	r11, qword [rsp + 160]
	imul  	r11, 2
	mov  	qword [rsp + 160], r11
	mov  	r11, qword [rsp + 160]
	mov  	qword [rsp + 152], r11
	add  	qword [rsp + 152], 1
	mov  	r11, qword [rsp + 152]
	mov  	qword [rsp + 144], r11
	mov  	r11, qword [rsp + 144]
	mov  	qword [rsp + 328], r11
	jmp  	adjustHeap.ifExit.9
adjustHeap.ifFalse.8:
adjustHeap.ifExit.9:
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 128], r11
	sal  	qword [rsp + 128], 3
	mov  	r11, qword [rsp + 128]
	add  	qword [rsp + 136], r11
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 120], r11
	mov  	r11, qword [rsp + 328]
	mov  	qword [rsp + 112], r11
	sal  	qword [rsp + 112], 3
	mov  	r11, qword [rsp + 112]
	add  	qword [rsp + 120], r11
	mov  	r11, qword [rsp + 136]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 104], r11
	mov  	r11, qword [rsp + 120]
	mov  	r11, qword [rsp + 104]
	cmp  	r11, qword [r11 + 0]
	mov  	r11, 0
	setg  	r11b
	mov  	qword [rsp + 104], r11
	cmp  	qword [rsp + 104], 1
	je  	adjustHeap.ifTrue.10
	jmp  	adjustHeap.ifFalse.11
adjustHeap.ifTrue.10:
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 96], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 88], r11
	sal  	qword [rsp + 88], 3
	mov  	r11, qword [rsp + 88]
	add  	qword [rsp + 96], r11
	mov  	r11, qword [rsp + 96]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 80], r11
	mov  	r11, qword [rsp + 80]
	mov  	qword [rsp + 72], r11
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 64], r11
	mov  	r11, qword [rsp + 344]
	mov  	qword [rsp + 56], r11
	sal  	qword [rsp + 56], 3
	mov  	r11, qword [rsp + 56]
	add  	qword [rsp + 64], r11
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 328]
	mov  	qword [rsp + 40], r11
	sal  	qword [rsp + 40], 3
	mov  	r11, qword [rsp + 40]
	add  	qword [rsp + 48], r11
	mov  	r11, qword [rsp + 48]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 32], r11
	mov  	r11, qword [rsp + 64]
	mov  	r11, qword [rsp + 32]
	mov  	qword [r11 + 0], r11
	mov  	r11, qword [rsp + 232]
	mov  	r11, qword [r11 + 0]
	mov  	qword [rsp + 24], r11
	mov  	r11, qword [rsp + 328]
	mov  	qword [rsp + 16], r11
	sal  	qword [rsp + 16], 3
	mov  	r11, qword [rsp + 16]
	add  	qword [rsp + 24], r11
	mov  	r11, qword [rsp + 72]
	mov  	qword [rsp + 8], r11
	mov  	r11, qword [rsp + 24]
	mov  	r11, qword [rsp + 8]
	mov  	qword [r11 + 0], r11
	mov  	r11, qword [rsp + 328]
	mov  	qword [rsp + 0], r11
	mov  	r11, qword [rsp + 0]
	mov  	qword [rsp + 344], r11
	jmp  	adjustHeap.ifExit.12
adjustHeap.ifFalse.11:
	jmp  	adjustHeap.whileExit.13
adjustHeap.ifExit.12:
	jmp  	adjustHeap.whileHeader.2
adjustHeap.whileExit.13:
	mov  	rax, 0
adjustHeap.exit.14:
	add  	rsp, 360
	ret

section .data
	dq  	3
_string_constant_0:
	db  	" ", 0
	dq  	3
_string_constant_1:
	db  	"", 10, 0

section .bss
@n:
	resq  	1
@a:
	resq  	1

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

