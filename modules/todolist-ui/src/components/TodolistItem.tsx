import { FunctionComponent, useState } from "react";
import { TText } from "./utils/Texts";
import { TextType } from "../model/utils/TextType";
import { Button, Checkbox, Tooltip } from "@mui/material";
import { Colors } from "../utils/Colors";
import '../styles/todolist-item.css';

interface TodolistItemProps {
    isDone: boolean;
}

export const TodolistItem: FunctionComponent<TodolistItemProps> = ({isDone}) => {

    const [content, setContent] = useState<string>("Buy groceries and something more.");
    const [isCheck, setIsCheck] = useState<boolean>(isDone);

    const onCheck = () => {
        setIsCheck(prev => !prev);
    }

    return (
        <div className="item">
            <div className="item-first-part">
                <Checkbox checked={isCheck} onChange={onCheck} sx={{color: Colors.PRIMARY_ORANGE, '&.Mui-checked': {color: Colors.PRIMARY_ORANGE}}}/>                
                <Tooltip title={content} placement="top">
                    <div className="item-content">
                        <TText type={TextType.TEXT}>{content}</TText>
                    </div>
                </Tooltip>
            </div>

            <Button sx={{ textTransform: 'none' }}>Edit</Button>
        </div>
    );
}