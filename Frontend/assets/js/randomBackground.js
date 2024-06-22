    const images = [
        "url('../img/pic_01.avif')",
        "url('../img/pic_02.avif')",
        "url('../img/pic_03.avif')",
        "url('../img/pic_04.avif')",
        "url('../img/pic_05.avif')",
        "url('../img/pic_06.avif')",
        "url('../img/pic_07.avif')",
        "url('../img/pic_08.avif')",
        "url('../img/pic_09.avif')",
        "url('../img/pic_10.avif')",
        "url('../img/pic_11.avif')",
        "url('../img/pic_12.avif')",
        "url('../img/pic_13.avif')",
        "url('../img/pic_14.avif')",
        "url('../img/pic_15.avif')",
        "url('../img/pic_16.avif')",
        "url('../img/pic_17.avif')",
        "url('../img/pic_18.avif')",
        "url('../img/pic_19.avif')",
    ];

    export function randombg(body) {
        const bg = images[Math.floor(Math.random() * images.length)]
        body.style.backgroundImage = bg
    }

    export function initBackgroundChanger(interval = 8000) {
        const body = document.querySelector('body')
        randombg(body)
        setInterval(() => randombg(body), interval)
    }
