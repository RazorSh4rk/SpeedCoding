const
    c = document.getElementById('c')
ctx = c.getContext('2d')
size = 20
cW = 0
cH = 0
elems = Math.floor(window.innerWidth / size)

c.width = window.innerWidth
c.height = window.innerHeight - 1

function line(f) {
    ctx.strokeStyle = 'white'
    if (f) {
        ctx.moveTo(cW, cH)
        ctx.lineTo(cW + size, cH + size)
        ctx.stroke()
    } else {
        ctx.moveTo(cW, cH + size)
        ctx.lineTo(cW + size, cH)
        ctx.stroke()
    }
    cW += size
    if(cW / size > elems){
        cH += size
        cW = 0
    }
}

/* MAIN */
ctx.fillStyle = 'black'
ctx.fillRect(0, 0, c.width, c.height)
setInterval(function(){
    line(Math.random() < 0.50)
}, 100 )