yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title('Star Songs')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/bootstrap.min.css')
        script(src: '/js/bootstrap.min.js', '')
    }
    body {
        div (style: 'background-image: url(images/starry-night.png); width: 100%; height: 100vh; background-repeat: no-repeat; background-size: cover;') {
            h3(class: 'text-center', style: 'color: LemonChiffon;', 'Star Songs')
            div(class: 'row justify-content-center') {
                div(class: 'col-auto') {
                    table(class: 'table table-responsive', style: 'background-color: #0D0938;') {
                        tr {
                            th(style: 'color: LemonChiffon;', 'Song')
                            th(style: 'color: LemonChiffon;', 'Artist')
                        }
                      songs.each { song ->
                        tr {
                            td() {
                                a(href:"$song.url", target: '_blank', rel: 'noopener noreferrer',
                                    "$song.title")
                            }
                            td(style: 'color: gold;', "${artistMapById[song.artistId].name}")
                        }
                      }
                    }
                }
            }
        }
    }
}
