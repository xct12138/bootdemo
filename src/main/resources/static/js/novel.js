//import "./vue"

let novelForm = new Vue({
    el:"#download-novel-form",
    data:{
        novelName:"",
        novelUrl:"",
        order:true,
        chapterListPosition:{
            attribute:'',
            value:'',
            index:0
        },
        chapterContentPosition:{
            attribute:'',
            value:'',
            index:0
        },
        replacementInputList:[{
            regex:'',
            replacement:''
        }]
    },
    methods:{
        addReplaceInput:function (){
            this.replacementInputList.push({
                regex:'',
                replacement:''
            })

        },
        download:function (){
            let data = {
                novelName:this.novelName,
                novelUrl:this.novelUrl,
                order:this.order,
                chapterListPosition:this.chapterListPosition,
                chapterContentPosition:this.chapterContentPosition,
                replacements:this.replacementInputList
            }
            console.log(data)
            axios.post("/novel/download",data).then(function (resp) {
                console.log(resp.data)
            })
        },
        regexRight:function (regex){
            let b
            try {
                new RegExp(regex)
                b = true
            }catch {
                b = false
            }
            return b
        }
    }
})

