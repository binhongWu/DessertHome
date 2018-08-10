		//��ȡ�����б��
	var year = document.getElementById("year");
	var month = document.getElementById("month");
	var day = document.getElementById("day");
	var year1 = document.getElementById("year1");
	var month1 = document.getElementById("month1");
	var day1 = document.getElementById("day1");
	var S_discount = document.getElementById("S_discount");
	
	//��ʼ�����������б��ѡ��
	function initData(){
		for(var i=0;i<=9;i++){
		S_discount.add(new Option(i+"��",i));
		}
		for(var i = 2017; i <= 2022; i++){
			year.add(new Option(i,i));
			year1.add(new Option(i,i));
		}
		for(var i = 1; i <= 12; i++){
			month.add(new Option(i,i));
			month1.add(new Option(i,i));
		}
	}
	window.onload = initData;
	
	//������ʾ����
	function showDay(){
		//�ж��Ƿ�����Ч������ֵ
		if(year.selectedIndex > 0 && month.selectedIndex > 0){
			//�������
			day.length = 1;
			//��ȡѡ�����ݺ��·�
			var y = parseInt(year.value);
			var m = parseInt(month.value);
			var d;
			//�ж��·�
			switch(m){
				case 2:
					//�ж���
					if(y%4 == 0 && y%100 != 0 || y%400 == 0)
						d = 29;
					else
						d = 28;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					d = 30;
					break;
				default:
					d = 31;	
			}
			//�������
			for(var i = 1; i <= d; i++){
				day.add(new Option(i,i));
			}
		}
	}
	//�����������б��ѡ���¼�
	year.onchange = showDay;
	month.onchange = showDay;
		//������ʾ����
	function showDay1(){
		//�ж��Ƿ�����Ч������ֵ
		if(year1.selectedIndex > 0 && month1.selectedIndex > 0){
			//�������
			day1.length = 1;
			//��ȡѡ�����ݺ��·�
			var y = parseInt(year1.value);
			var m = parseInt(month1.value);
			var d;
			//�ж��·�
			switch(m){
				case 2:
					//�ж���
					if(y%4 == 0 && y%100 != 0 || y%400 == 0)
						d = 29;
					else
						d = 28;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					d = 30;
					break;
				default:
					d = 31;	
			}
			//�������
			for(var i = 1; i <= d; i++){
				day1.add(new Option(i,i));
			}
		}
	}
	//�����������б��ѡ���¼�
	year1.onchange = showDay1;
	month1.onchange = showDay1;
	function validate(){
		if(!SubjectName1()||!SubjectBeginTime1()||!SubjectEndTime1()||!S_discount1()||!subjectImg1()||!SubjectIntroduce1()||!timeerror()){
			return false;
		}
	}
	function SubjectName1(){
		var SubjectName2=$("#SubjectName").val();	
		$("#SubjectNameerror").html("");
		if(SubjectName2==""){
			$("#SubjectNameerror").html("ר�����Ʋ���Ϊ��");
			return false;
		}
		return true;
	}
	function SubjectBeginTime1(){
		var yearBegin=$("#year").val();
		var monthBegin=$("#month").val();
		var dayBegin=$("#day").val();
		$("#SubjectBeginTimeerror").html("");
		if(yearBegin==""||monthBegin==""||dayBegin==""){
			$("#SubjectBeginTimeerror").html("ר�⿪ʼʱ�䲻��Ϊ��");
			return false;
		}
		return true;
	}
	function SubjectEndTime1(){
		var yearEnd=$("#year1").val();
		var monthEnd=$("#month1").val();
		var dayEnd=$("#day1").val();	
		$("#SubjectEndTimeerror").html("");
		if(yearEnd==""||monthEnd==""||dayEnd==""){
			$("#SubjectEndTimeerror").html("ר�����ʱ�䲻��Ϊ��");
			return false;
		}
		return true;
	}
	function S_discount1(){
		var S_discount2=$("#S_discount").val();	
		$("#S_discounterror").html("");
		if(S_discount2==""){
			$("#S_discounterror").html("ר���ۿ۲���Ϊ��");
			return false;
		}
		return true;
	}
	function SubjectIntroduce1(){
		var SubjectIntroduce2=$("#SubjectIntroduce").val();	
		$("#SubjectIntroduceerror").html("");
		if(SubjectIntroduce2==""){
			$("#SubjectIntroduceerror").html("ר����ܲ���Ϊ��");
			return false;
		}
		return true;
	}		
	function subjectImg1(){
		var subjectImgrule=/^(jpg|jpeg|png|gif|JPG|JPEG|PNG|GIF)$/;
		var subjectImg2=$("#subjectImg").val();	
		$("#pictureerror").html("");
		if(subjectImg2==""){
			$("#pictureerror").html("ר��ͼƬ����Ϊ��");
			return false;
		}
		else if(!subjectImgrule.test(subjectImg2.substring(subjectImg2.indexOf(".")+1))){
			
			$("#pictureerror").html("��ѡ��ͼƬ�ϴ�");
			
			return false;
		}
		return true;
		alert(subjectImg2);
	}
	function timeerror(){
		var yearEnd=$("#year1").val();
		var monthEnd=$("#month1").val();
		var dayEnd=$("#day1").val();
		var yearBegin=$("#year").val();
		var monthBegin=$("#month").val();
		var dayBegin=$("#day").val();
		$("#SubjectBeginTimeerror").html("");
		if(yearBegin<yearEnd){
			return true;
		}
		if(yearBegin==yearEnd&&monthBegin<monthEnd){
			return true;
		}
		if(yearBegin==yearEnd&&monthBegin==monthEnd&&dayBegin<=dayEnd){
			return true;
		}	
			$("#SubjectBeginTimeerror").html("��ʼʱ�䲻�����ڽ���ʱ��");
			return false;		
	}