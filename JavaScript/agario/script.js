const canv = document.getElementById('c')
const ctx = canv.getContext('2d')
const w = canv.width
const h = canv.height
let gameOver = false
let diff = 30
let maxFood = 15

let player = {
    'x' : w / 2,
    'y' : h / 2,
    'size' : 20,
    'speed' : 5,
    'score' : 0
}

class Food{
    constructor(x, y){
        this.x = x
        this.y = y
        this.size = 10
    }
    collides(p){
        return(
            (p.x - this.x) * (p.x - this.x)
            +
            (p.y - this.y) * (p.y - this.y)
            <=
            (p.size - this.size) * (p.size - this.size) + this.size
        )
    }
}
let food = [new Food(300, 300)]

//--------

update()
document.addEventListener('keydown', key)
function key(e){
    switch(e.key){
        case 'w':
            player.y -= player.speed
            break
        case 'a':
            player.x -= player.speed
            break
        case 's':
            player.y += player.speed
            break
        case 'd':
            player.x += player.speed
            break  
    }
    update()
}
setInterval(function(){
    food.push(new Food(Math.floor(Math.random() * w), Math.floor(Math.random() * h)))
    update()
}, 1000)

//--------

function drawCircle(x, y, size, color){
    ctx.fillStyle = color
    ctx.beginPath()
    ctx.arc(x, y, size, 0, 2 * Math.PI)
    ctx.fill()
}
function write(text, x, y){
    ctx.fillStyle = 'black'
    ctx.font = '15px Arial'
    ctx.fillText(text, x, y)
}
function reset(){
    ctx.fillStyle = 'white'
    ctx.fillRect(0, 0, w, h)
}
function update(){
    if(!gameOver && player.score < diff){        
        if(food.length >= maxFood){
            gameOver = true
        }
        reset()
        food.forEach(function(e){
            drawCircle(e.x, e.y, e.size, 'red')
            if(e.collides(player)){
                player.size += 3
                player.speed -= 0.1
                player.score += 1
                let index = food.indexOf(e)
                if(index > -1){
                    food.splice(index, 1)
                }
            }
        })
        drawCircle(player.x, player.y, player.size, 'green')
        write('score: ' + player.score + ' /' + diff, 10, 20)
    }
    if(gameOver){
        document.removeEventListener('keydown', key)
        reset()
        write('GAME OVER', w/2-20, h/2)
    }
    if(player.score >= diff){
        document.removeEventListener('keydown', key)
        reset()
        write('YOU WIN', w/2-20, h/2)
    }
}