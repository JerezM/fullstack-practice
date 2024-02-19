import { FunctionComponent, useState } from "react";
import { TText } from "./utils/Texts";
import { TextType } from "../model/utils/TextType";
import { TodolistItem } from "./TodolistItem";
import '../styles/todolist.css';

interface TodolistProps {}

export const Todolist: FunctionComponent<TodolistProps> = () => {

    const [itemToAddContent, setItemToAddContent] = useState<string>("");

    const handleChangeOnAddItem = (event: React.ChangeEvent<HTMLInputElement>) => {
        setItemToAddContent(event.target.value);
    };

    return (
        <div className="todolist">
            <div className="title">
                <TText type={TextType.HEADER2}>To-Do List</TText>
            </div>
            <div className="list">
                <TodolistItem isDone={false}/>
                <TodolistItem isDone={false}/>
                <TodolistItem isDone={false}/>
            </div>
            <div className="add-item">
                <input className="add-item-input" type="text" placeholder="New Task" value={itemToAddContent} onChange={handleChangeOnAddItem}/>
                <button>Add</button>
            </div>
        </div>
    );
}