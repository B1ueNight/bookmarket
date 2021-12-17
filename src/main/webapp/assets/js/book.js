// teacher.js
$(function() {
    $(".main_nemu a:nth-child(3)").addClass("active");
    $("#search_dep").click(function(){
        $(".department_search").css("display", "block");
    })
    $("del_search_close").click(function() {
        $(".department_search").css("display", "");
    });
    $("#dep_keyword").keyup(function(e){
        if(e.keyCode == 13) $("#dep_search_btn").trigger("click");
    })
    $("#dep_search_btn").click(function() {
        $.ajax({
            url:"/book/keyword?keyword="+$("#dep_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.list.length; i++){
                    let str_status = "";
                    if(r.list[i].bi_status == 1) str_status = "입고"
                    if(r.list[i].bi_status == 2) str_status = "입고예정"
                    if(r.list[i].bi_status == 3) str_status = "절판"
                    if(r.list[i].bi_status == 4) str_status = "리콜"

                    let tag=
                    '<li>'+
                        '<a href="#" data-dep-seq="'+r.list[i].bi_seq+'">'+r.list[i].bi_name+'</a>'+
                        '<span class="status'+r.list[i].bi_status+'">'+str_status+'</span>'+
                    '</li>';
                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault(); //a태그의 링크 기능 제거
                    let seq = $(this).attr("data-dep-seq");
                    let name = $(this).html();

                    $("#book_cate_name").attr("data-dep-seq", seq);
                    $("#book_cate_name").val(name);

                    $(".seacher_result ul").html("");
                    $("#dep_keyword").val("");
                    $(".department_search").css("display", "");
                })
            }
        })
    })

    $("#add_category").click(function(){

        let book_cate_name = $("#book_cate_name").attr("data-dep-seq");
        let book_name = $("#book_name").val();
        let book_sub= $("#book_sub").val();
        let book_writer = $("#book_writer").val();
        let book_company = $("#book_company").val();
        let book_category = $("#book_category").val();
        let book_status = $("#book_status option:selected").val();
        let book_stock = $("#book_stock").val();
        let book_point = $("#book_point").val();

        if(book_cate_name == undefined){
            alert("카테고리를 입력해주세요");
            return;
        }
        if(book_name== ''){
            alert("책 제목을 입력해주세요");
            return;
        }

        let data = {
            bi_ci_seq:book_cate_name,
            bi_name:book_name,
            bi_sub:teacher_number,
            bi_writer:book_writer,
            bi_company:book_company,
            bi_category:book_category,
            bi_status:book_status,
            bi_stock:book_stock,
            bi_point:book_point
        }

        $.ajax({
            url:"/book/add",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(e) {
                alert(e.message);
                if(e.status)
                    location.reload();
                }
            })
        })
            
    $("#add_department").click(function(){
        $(".popup_wrap").css("display", "block");
        $("#modify_dep").css("display", "none");
        $("#cancel_dep").css("display", "inline-block");
        $(".popup .top_area h2").html("교직원 추가");
        $(".popup .top_area p").html("교직원 정보를 입력하세요");
    })
    $("#cancel_dep").click(function(){
        if(confirm("취소하시겠습니까?\n(입력한 내용은 저장되지 않습니다.)") == false) return;

        $("teacher_dep_name").attr("data-dep-seq", "");
        $("#teacher_dep_name").val("");
        $("#teacher_name").val("");
        $("#teacher_number").val("");
        $("#teacher_pwd").val("");
        $("#teacher_pwd_confirm").val("");
        $("#teacher_birth").val("");
        $("#teacher_phone").val("");
        $("#teacher_email").val("");
        
        $(".popup_wrap").css("display", "")
    })


    $("#search_btn").click(function(){
        let type = $("#search_type option:selected").val();
        let keyword = $("#keyword").val();

        location.href = "/teacher?type="+type+"&keyword="+keyword;
    })
})