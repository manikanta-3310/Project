// Songs Data
const songs = [
  { title: "Shape Of You", artist: "Ed Sheeran", genre: "pop", cover: "https://i.scdn.co/image/ab67616d0000b273bdb3ea932ae92d4bc19d8b79", src: "songs/shapeofyou.mp3" },
  { title: "All Of Me", artist: "Adele", genre: "pop", cover: "https://linktoimage.com/adele.jpg", src: "songs/allofme.mp3" },
  { title: "Wonderwall", artist: "Oasis", genre: "rock", cover: "https://linktoimage.com/wonderwall.jpg", src: "songs/wonderwall.mp3" }
];

const songList = document.getElementById("songList");
const genreSelect = document.getElementById("genre");
const cover = document.getElementById("cover");
const songTitle = document.getElementById("songTitle");
const artist = document.getElementById("artist");
const audioPlayer = document.getElementById("audioPlayer");
const playlistName = document.getElementById("playlistName");
const createPlaylistBtn = document.getElementById("createPlaylistBtn");
const currentPlaylist = document.getElementById("currentPlaylist");
const allPlaylists = document.getElementById("allPlaylists");
const darkModeToggle = document.getElementById("darkModeToggle");

let playlists = {};
let currentSongs = [];

// Load Song List
function loadSongs(filter = "all") {
  songList.innerHTML = "";
  songs
    .filter(song => filter === "all" || song.genre === filter)
    .forEach(song => {
      const btn = document.createElement("button");
      btn.textContent = `${song.title} - ${song.artist}`;
      btn.addEventListener("click", () => playSong(song));
      songList.appendChild(btn);
    });
}

function playSong(song) {
  cover.src = song.cover;
  songTitle.textContent = song.title;
  artist.textContent = song.artist;
  audioPlayer.src = song.src;
  audioPlayer.play();
  currentSongs.push(song.title);
  updateCurrentPlaylist();
}

function updateCurrentPlaylist() {
  currentPlaylist.innerHTML = "";
  currentSongs.forEach(song => {
    const li = document.createElement("li");
    li.textContent = song;
    currentPlaylist.appendChild(li);
  });
}

createPlaylistBtn.addEventListener("click", () => {
  const name = playlistName.value.trim();
  if (!name) return alert("Enter a playlist name!");
  playlists[name] = [...currentSongs];
  const li = document.createElement("li");
  li.textContent = `${name} (${currentSongs.length} songs)`;
  allPlaylists.appendChild(li);
  playlistName.value = "";
});

genreSelect.addEventListener("change", e => loadSongs(e.target.value));

// Dark Mode Toggle
darkModeToggle.addEventListener("change", () => {
  document.body.classList.toggle("dark");
});

// Initial Load
loadSongs();
