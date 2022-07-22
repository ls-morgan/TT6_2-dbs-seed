import React, { useMemo } from 'react'
import { useTable } from 'react-table'
import MOCK_DATA from './exchangeRate.json'
import { COLUMNS } from './exchangeColumns'
import './table.css'

export const ExchangeTable = () => {

    const columns = useMemo(() => COLUMNS, []);
    const data = useMemo(() => MOCK_DATA, []);

    const tableInstance = useTable({
        columns,
        data
    })

    const { 
        getTableProps, 
        getTableBodyProps, 
        headerGroup, 
        rows, 
        prepareRows,
    } = tableInstance

  return (
    <table {...getTableProps()}>
        <thead>
            {
                headerGroup.map((headerGroup) => (
                    <tr {... headerGroup.getHeaderGroupProps()}>
                        {
                            headerGroup.headers.map((column) => (
                                <th {...column.getHeadrProps()}>
                                    {
                                        column.render('Header')
                                    }
                                </th>
                            ))
                        }
                    </tr>
            ))}
            
        </thead>
        <tbody {...getTableBodyProps()}>
            {
                rows.map(row => {
                    prepareRows(row)
                    return(<tr {...row.getRowProps()}>
                    {
                        rows.cells.map( cell => {
                            return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                        })
                    }
                </tr>)
                })
            }
        </tbody>
    </table>
  )
}

export default ExchangeTable
