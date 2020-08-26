import React, {useEffect, useState} from 'react';
import axios from 'axios';

export const Album = ({id, name, images}) => (<div key={id} className={"album"}>
    <h2>{name}</h2>
    <div className={"images"}>
        {images.map((img, index) => (
            <img key={index} src={img.url} alt={"No idea what it is about"} width="400"/>))}
    </div>
</div>)

export const Albums = () => {
    const [albums, setAlbums] = useState([]);
    useEffect(() => {
        async function getAlbums() {
            const {data} = await axios.get(`${process.env.REACT_APP_BACKEND_URL}/albums`)
            setAlbums(data);
        }

        getAlbums();
    }, [])
    return (<div className={"albums"}>{albums.map(album => (
        <Album key={album.id} id={album.id} name={album.name} images={album.images}/>))}</div>);
}
